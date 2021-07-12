package qlks_hdv.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.BookingCard;

public interface BookingCardRepository extends JpaRepository<BookingCard, Integer> {

  List<BookingCard> findAllByCustomerUserUsername(String username);

  Optional<BookingCard> findBookingCardByStatusAndCustomerUserUsername(String status,
      String username);

  Boolean existsBookingCardByStatusAndCustomerUserUsername(String status, String username);

  Boolean existsBookingCardByBookingIdAndAndCustomerUserUsername(Integer bookingId,
      String username);

  Boolean existsByCustomerId(Integer id);

}
