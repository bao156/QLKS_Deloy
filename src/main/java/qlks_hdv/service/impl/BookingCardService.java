package qlks_hdv.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qlks_hdv.entity.BookingCard;
import qlks_hdv.entity.Customer;
import qlks_hdv.entity.Discount;
import qlks_hdv.exception.NotFoundException;
import qlks_hdv.mapper.BookingCardMapper;
import qlks_hdv.repository.BookingCardRepository;
import qlks_hdv.repository.CustomerRepository;
import qlks_hdv.repository.DiscountRepository;
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


  @Override
  @Transactional
  public void createBookingCard(CreateBookingCardRequest createBookingCardRequest) {

    Customer customer = customerRepository
        .findByUserUsername(createBookingCardRequest.getUsername())
        .orElseThrow(() -> new NotFoundException("customer-not-found"));

    Discount discount = discountRepository
        .findByDiscountName(createBookingCardRequest.getDiscountName());

    BookingCard bookingCard = bookingCardMapper
        .mapToBookingCard(createBookingCardRequest, 0, "Reservated", customer, discount);

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


}
