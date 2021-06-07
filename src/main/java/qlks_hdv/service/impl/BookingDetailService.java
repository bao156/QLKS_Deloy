package qlks_hdv.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qlks_hdv.entity.BookingCard;
import qlks_hdv.entity.BookingDetail;
import qlks_hdv.entity.RoomType;
import qlks_hdv.exception.NotFoundException;
import qlks_hdv.mapper.BookingDetailMapper;
import qlks_hdv.repository.BookingCardRepository;
import qlks_hdv.repository.BookingDetailRepository;
import qlks_hdv.repository.RoomTypeRepository;
import qlks_hdv.request.CreateBookingCardRequest;
import qlks_hdv.request.CreateBookingDetailRequest;
import qlks_hdv.response.GetBookingDetailResponse;
import qlks_hdv.service.IBookingDetailService;

@Data
@RequiredArgsConstructor
@Service
public class BookingDetailService implements IBookingDetailService {

  private final BookingCardRepository bookingCardRepository;
  private final BookingDetailRepository bookingDetailRepository;
  private final RoomTypeRepository roomTypeRepository;
  private final BookingDetailMapper bookingDetailMapper;
  private final BookingCardService bookingCardService;

  @Override
  @Transactional
  public void addBookingDetail(CreateBookingDetailRequest createBookingDetailRequest) {

    if (!bookingCardRepository.existsBookingCardByStatusAndCustomerUserUsername("Reservated",
        createBookingDetailRequest.getUsername())) {
      bookingCardService.createBookingCard(
          new CreateBookingCardRequest(createBookingDetailRequest.getUsername(), ""));
    }

    BookingCard bookingCard = bookingCardRepository
        .findBookingCardByStatusAndCustomerUserUsername("Reservated",
            createBookingDetailRequest.getUsername())
        .orElseThrow(() -> new NotFoundException("booking-card-not-found"));

    RoomType roomType = roomTypeRepository.findByName(createBookingDetailRequest.getNameType())
        .orElseThrow(() -> new NotFoundException("roomtype-not-found"));

    BookingDetail bookingDetail = bookingDetailMapper
        .mapToBookingDetail(createBookingDetailRequest, bookingCard, roomType);
    bookingDetail.setBookingCard(bookingCard);
    bookingDetail.setType(roomType);
    bookingDetailRepository.save(bookingDetail);
  }

  @Override
  public List<GetBookingDetailResponse> gettBookingDetailByBookingCardId(Integer bookingId) {
    List<BookingDetail> bookingDetailList = bookingDetailRepository
        .findAllByBookingCardBookingId(bookingId);

    return bookingDetailList.stream()
        .map(detail -> bookingDetailMapper.mapToGetBookingDetailResponse(detail))
        .collect(Collectors.toList());
  }


}
