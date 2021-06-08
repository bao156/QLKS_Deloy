package qlks_hdv.service.impl;

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


}
