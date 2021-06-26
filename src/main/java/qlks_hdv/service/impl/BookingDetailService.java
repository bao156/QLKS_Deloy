package qlks_hdv.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import qlks_hdv.entity.BookingCard;
import qlks_hdv.entity.BookingDetail;
import qlks_hdv.entity.Price;
import qlks_hdv.entity.Room;
import qlks_hdv.entity.RoomType;
import qlks_hdv.entity.compositekey.BookingDetailId;
import qlks_hdv.entity.compositekey.PriceId;
import qlks_hdv.exception.BadRequestException;
import qlks_hdv.exception.NotFoundException;
import qlks_hdv.mapper.BookingDetailMapper;
import qlks_hdv.mapper.RoomTypeMapper;
import qlks_hdv.repository.BookingCardRepository;
import qlks_hdv.repository.BookingDetailRepository;
import qlks_hdv.repository.PricesRepository;
import qlks_hdv.repository.RoomRepostiory;
import qlks_hdv.repository.RoomTypeRepository;
import qlks_hdv.request.CreateBookingCardRequest;
import qlks_hdv.request.CreateBookingDetailRequest;
import qlks_hdv.response.GetBookingDetailResponse;
import qlks_hdv.response.GetRoomTypeWithPriceResponse;
import qlks_hdv.service.IBookingDetailService;

@Data
@Lazy
@RequiredArgsConstructor
@Service
public class BookingDetailService implements IBookingDetailService {

  private final BookingCardRepository bookingCardRepository;
  private final BookingDetailRepository bookingDetailRepository;
  private final RoomTypeRepository roomTypeRepository;
  private final BookingDetailMapper bookingDetailMapper;
  private final BookingCardService bookingCardService;
  private final PricesRepository pricesRepository;
  private final RoomTypeMapper roomTypeMapper;
  private final RoomRepostiory roomRepostiory;

  @Override
  @Transactional
  public void addBookingDetail(CreateBookingDetailRequest createBookingDetailRequest) {

    //TÌM ĐỂ KIỂM TRA CÓ BAO NHIÊU PHÒNG LOẠI ĐÓ
    List<Room> roomListWithIdType = roomRepostiory
        .findAllByTypeId(createBookingDetailRequest.getTypeId());

    Integer quantityRoomWithTypeId = roomListWithIdType.size();

    List<BookingDetail> bookingDetailList = bookingDetailRepository
        .findAllByBackAtAfterAndTypeId(
            downToDate(createBookingDetailRequest.getRecieveAt()),
            createBookingDetailRequest.getTypeId());

    List<Date> getDatesFromRecieveToBack = getDatesFromTo(createBookingDetailRequest.getRecieveAt(),
        createBookingDetailRequest.getBackAt());

    for (Date date : getDatesFromRecieveToBack) {
      int countBooking = 0;
      for (BookingDetail bookingDetail : bookingDetailList) {
        if (date.before(changeStringToDate(createBookingDetailRequest.getBackAt()))) {
          countBooking += bookingDetail.getAmount();
        }
      }
      if (countBooking == quantityRoomWithTypeId) {
        throw new BadRequestException("room-is-full-for-type-on-that-date");
      }

      if ((countBooking + createBookingDetailRequest.getAmount()) > quantityRoomWithTypeId) {
        throw new BadRequestException(
            String.valueOf(quantityRoomWithTypeId - countBooking) + "-room-left-for-type");
      }
    }

    if (!bookingCardRepository.existsBookingCardByStatusAndCustomerUserUsername("Processing",
        createBookingDetailRequest.getUsername())) {
      bookingCardService.createBookingCard(
          new CreateBookingCardRequest(createBookingDetailRequest.getUsername(), ""));
    }

    BookingCard bookingCard = bookingCardRepository
        .findBookingCardByStatusAndCustomerUserUsername("Processing",
            createBookingDetailRequest.getUsername())
        .orElseThrow(() -> new NotFoundException("booking-card-not-found"));

    RoomType roomType = roomTypeRepository.findById(createBookingDetailRequest.getTypeId())
        .orElseThrow(() -> new NotFoundException("roomtype-not-found"));

    BookingDetail bookingDetail = bookingDetailMapper
        .mapToBookingDetail(createBookingDetailRequest, bookingCard, roomType);
    bookingDetail.setPrice(getPriceOfARoom(createBookingDetailRequest.getRecieveAt(),
        createBookingDetailRequest.getBackAt(), createBookingDetailRequest.getTypeId()));
    bookingDetailRepository.save(bookingDetail);
    bookingCardService.updatePriceOfBookingCard(bookingCard.getBookingId());
    bookingCardService.updateCompleteDateOnBookingCard(bookingCard.getBookingId());

  }

  @Override
  public List<GetBookingDetailResponse> getBookingDetailByBookingCardId(Integer bookingId,
      String username) {

    if (bookingId == 0) {
      BookingCard bookingCard = bookingCardRepository
          .findBookingCardByStatusAndCustomerUserUsername("Processing", username)
          .orElseThrow(() -> new NotFoundException("booking-not-exist"));
      bookingId = bookingCard.getBookingId();
    }

    if (!bookingCardRepository
        .existsBookingCardByBookingIdAndAndCustomerUserUsername(bookingId, username)) {
      throw new NotFoundException("booking-card-not-found");
    }
    List<BookingDetail> bookingDetailList = bookingDetailRepository
        .findAllByBookingCardBookingId(bookingId);

    return bookingDetailList.stream()
        .map(detail -> bookingDetailMapper.mapToGetBookingDetailResponse(detail))
        .collect(Collectors.toList());
  }

  @Override
  public Integer getPriceOfARoom(String recieveDate, String backDate, Integer typeId) {
    Price priceAtWeekend = pricesRepository.findById(new PriceId(typeId, true))
        .orElseThrow(() -> new NotFoundException("type-not-found"));
    Price priceAtNormal = pricesRepository.findById(new PriceId(typeId, false))
        .orElseThrow(() -> new NotFoundException("type-not-found"));
    int price = 0;
    try {
      Date d_from = new SimpleDateFormat("yyyy-MM-dd").parse(recieveDate);
      Date d_to = new SimpleDateFormat("yyyy-MM-dd").parse(backDate);

      long t1 = d_from.getTime();
      long t2 = d_to.getTime();

      SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

      if (t1 < t2) {
        //1 = 1000
        for (long i = t1; i <= t2; i += 86400000) {
          Date date = new SimpleDateFormat("yyyy-MM-dd").parse(f.format(i));
          Calendar calendar = Calendar.getInstance();
          calendar.setTime(date);
          int dateOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
          price += (dateOfWeek == 7 || dateOfWeek == 1) ? priceAtWeekend.getPrice()
              : priceAtNormal.getPrice();
        }
      }
    } catch (Exception e) {
      throw new BadRequestException("invalid-date");
    }
    return price;
  }

  @Override
  public List<GetRoomTypeWithPriceResponse> getRoomTypeBookingRank() {
    List<GetRoomTypeWithPriceResponse> roomTypeRank = new ArrayList<>();
    List<BookingDetail> bookingDetailList = bookingDetailRepository.findAll();
    List<RoomType> roomTypeList = roomTypeRepository.findAll();
    HashMap<RoomType, Integer> getTimesBooking = new HashMap<>();
    for (RoomType roomType : roomTypeList) {
      getTimesBooking.put(roomType, 0);
    }

    for (BookingDetail bookingDetail : bookingDetailList) {
      getTimesBooking
          .put(bookingDetail.getType(), getTimesBooking.get(bookingDetail.getType()) + 1);
    }
    List<Integer> listTempTimes = new ArrayList<>();
    for (Integer i : getTimesBooking.values()) {
      listTempTimes.add(i);
    }
    Collections.sort(listTempTimes, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1 > o2 ? -1 : 1;
      }
    });

    for (Integer i : listTempTimes) {
      for (RoomType j : getTimesBooking.keySet()) {
        if (i == getTimesBooking.get(j)) {
          Date now = new Date();
          Calendar calendar = Calendar.getInstance();
          calendar.setTime(now);
          int date = calendar.get(Calendar.DAY_OF_WEEK);
          boolean isWeekend = (date == 7 || date == 1) ? true : false;
          PriceId priceId = new PriceId(j.getId(), isWeekend);
          Price price = pricesRepository.getOne(priceId);
          GetRoomTypeWithPriceResponse getRoomTypeWithPriceResponse = roomTypeMapper
              .mapToGetRoomTypeWithPriceResponse(j, price.getPrice());
          if (!roomTypeRank.contains(getRoomTypeWithPriceResponse)) {
            roomTypeRank.add(getRoomTypeWithPriceResponse);
          }
        }
      }
    }
    return roomTypeRank;
  }

  @Override
  @Transactional
  public void deleteDetailBooking(BookingDetailId bookingDetailId) {
    BookingDetail bookingDetail = bookingDetailRepository.findById(bookingDetailId)
        .orElseThrow(() -> new NotFoundException("booking-detail-not-exist"));
    bookingDetailRepository.delete(bookingDetail);
    bookingCardService.updatePriceOfBookingCard(bookingDetailId.getBookingCard());
  }

  @Override
  public String downToDate(String date) {
    Integer day = Integer.parseInt(date.substring(date.length() - 2, date.length()));
    String dayToString = "";
    day--;
    dayToString = day < 10 ? "0" + String.valueOf(day) : String.valueOf(day);
    dayToString = date.substring(0, date.length() - 2) + dayToString;
    return dayToString;

  }

  @Override
  public List<Date> getDatesFromTo(String dateFrom, String dateTo) {
    List<Date> listDayFromTo = new ArrayList<>();
    try {
      Date d_from = new SimpleDateFormat("yyyy-MM-dd").parse(dateFrom);
      Date d_to = new SimpleDateFormat("yyyy-MM-dd").parse(dateTo);

      long t1 = d_from.getTime();
      long t2 = d_to.getTime();

      SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

      if (t1 < t2) {
        //1 = 1000
        for (long i = t1; i <= t2; i += 86400000) {
          Date date = new SimpleDateFormat("yyyy-MM-dd").parse(f.format(i));
          listDayFromTo.add(date);
        }
      }
    } catch (Exception e) {
      throw new BadRequestException("invalid-date");
    }
    return listDayFromTo;
  }

  @Override
  public Date changeStringToDate(String dateString) {
    Date date = new Date();
    try {
      date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
    } catch (ParseException e) {
      throw new BadRequestException("invalid-date");
    }
    return date;
  }

}
