package qlks_hdv.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.BookingCard;

public interface BookingCardRepository extends JpaRepository<BookingCard, Integer> {

  List<BookingCard> findAllByCustomerUserUsername(String username);

}
