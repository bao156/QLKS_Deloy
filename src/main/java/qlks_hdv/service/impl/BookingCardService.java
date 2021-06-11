package qlks_hdv.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import qlks_hdv.entity.BookingCard;
import qlks_hdv.entity.BookingDetail;
import qlks_hdv.entity.Customer;
import qlks_hdv.entity.Discount;
import qlks_hdv.entity.ServiceDetail;
import qlks_hdv.exception.BadRequestException;
import qlks_hdv.exception.NotFoundException;
import qlks_hdv.mapper.BookingCardMapper;
import qlks_hdv.repository.BookingCardRepository;
import qlks_hdv.repository.BookingDetailRepository;
import qlks_hdv.repository.CustomerRepository;
import qlks_hdv.repository.DiscountRepository;
import qlks_hdv.repository.ServiceDetailRepository;
import qlks_hdv.request.CreateBookingCardRequest;
import qlks_hdv.response.GetBookingCardReponse;
import qlks_hdv.service.IBookingCardService;

@Data
@RequiredArgsConstructor
@Service
public class BookingCardService implements IBookingCardService {

  private final BookingCardRepository bookingCardRepository;
  private final DiscountRepository discountRepository;
  private final CustomerRepository customerRepository;
  private final BookingCardMapper bookingCardMapper;
  private final ServiceDetailRepository serviceDetailRepository;
  private final BookingDetailRepository bookingDetailRepository;


  @Override
  @Transactional
  public void createBookingCard(CreateBookingCardRequest createBookingCardRequest) {

    Customer customer = customerRepository
        .findByUserUsername(createBookingCardRequest.getUsername())
        .orElseThrow(() -> new NotFoundException("customer-not-found"));

    Discount discount = discountRepository
        .findByDiscountName(createBookingCardRequest.getDiscountName());

    BookingCard bookingCard = bookingCardMapper
        .mapToBookingCard(createBookingCardRequest, 0, "Processing", customer, discount);

    bookingCardRepository.save(bookingCard);

  }

  @Override
  public List<GetBookingCardReponse> getAllBookingCardsByUsername(String username) {
    List<BookingCard> bookingCardList = bookingCardRepository
        .findAllByCustomerUserUsername(username);
    List<GetBookingCardReponse> getBookingCardReponseList = bookingCardList.stream()
        .map(develop -> bookingCardMapper.mapToGetBookingCardReponse(develop))
        .collect(Collectors.toList());
    return getBookingCardReponseList;
  }

  @Override
  @Transactional
  public void updatePriceOfBookingCard(int bookingId) {

    BookingCard bookingCard = bookingCardRepository.findById(bookingId)
        .orElseThrow(() -> new NotFoundException("booking-card-not-found"));

    List<BookingDetail> bookingDetailList = bookingDetailRepository
        .findAllByBookingCardBookingId(bookingId);

    List<ServiceDetail> serviceDetailList = serviceDetailRepository
        .findAllByBookingCardBookingId(bookingId);

    int price = 0;
    price = bookingDetailList.stream()
        .mapToInt(sla -> sla.getPrice() * sla.getAmount())
        .sum();

    price += serviceDetailList.stream()
        .mapToInt(sla -> sla.getPrice() * sla.getQuantity())
        .sum();

    bookingCard.setPrice(price);
    bookingCardRepository.save(bookingCard);

  }

  @Override
  @Transactional
  public void updateCompleteDateOnBookingCard(int bookingId) {

    BookingCard bookingCard = bookingCardRepository.findById(bookingId)
        .orElseThrow(() -> new NotFoundException("booking-card-not-found"));

    Sort sort = Sort.by(Direction.DESC, "backAt");
    List<BookingDetail> bookingDetailList = bookingDetailRepository
        .findAllByBookingCardBookingId(bookingId, sort);

    bookingCard.setCompleteAt(bookingDetailList.get(0).getBackAt());
    bookingCardRepository.save(bookingCard);

  }


  @Override
  @Transactional
  public void changeStatusBookingCard(Integer bookingId, Boolean isCancel) {

    BookingCard bookingCard = bookingCardRepository.findById(bookingId)
        .orElseThrow(() -> new NotFoundException("booking-card-not-found"));
    if (isCancel) {
      if (bookingCard.getStatus().equals("Completed") || bookingCard.getStatus()
          .equals("Renting")) {
        throw new BadRequestException("can-not-disable");
      }
      bookingCard.setStatus("Cancel");
    } else {
      if (bookingCard.getStatus().equals("Completed")) {
        throw new BadRequestException("can-not-change");
      }
      if (bookingCard.getStatus().equals("Processing")) {
        bookingCard.setStatus("Reservated");
      } else if (bookingCard.getStatus().equals("Reservated")) {
        bookingCard.setStatus("Renting");
      } else if (bookingCard.getStatus().equals("Renting")) {
        bookingCard.setStatus("Completed");
      }
    }

    bookingCardRepository.save(bookingCard);

  }

  @Override
  public HashMap<Integer, Integer> getRevenueAtDate(int year) {
    HashMap<Integer, Integer> revenueList = new HashMap<>();
    List<BookingCard> bookingCardList = bookingCardRepository.findAll();
    for (int i = 0; i < 12; i++) {
      revenueList.put(i, 0);
    }
    year = year - 1900;
    for (BookingCard bookingCard : bookingCardList) {
      List<Integer> tempList = getPriceCalcute(year, bookingCard);
      revenueList.put(tempList.get(0), (tempList.get(1) + revenueList.get(tempList.get(0))));
    }
    return revenueList;
  }

  @Override
  public List<Integer> getPriceCalcute(int year, BookingCard bookingCard) {
    List<Integer> priceAndMonth = new ArrayList<>();
    priceAndMonth.add(0);
    priceAndMonth.add(0);
    try {
      Date revenueAt = new SimpleDateFormat("yyyy-MM-dd").parse(bookingCard.getCompleteAt().trim());
      Calendar cal = Calendar.getInstance();
      cal.setTime(revenueAt);
      int monthTemp = cal.getTime().getMonth();
      int yearTemp = cal.getTime().getYear();
      priceAndMonth.set(0, monthTemp);
      priceAndMonth.set(1, 0);
      if (yearTemp == year) {
        priceAndMonth.set(1, bookingCard.getPrice());
      }

    } catch (ParseException e) {
    }
    return priceAndMonth;
  }

}
