package qlks_hdv.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.BookingDetail;
import qlks_hdv.entity.compositekey.BookingDetailId;

public interface BookingDetailRepository extends JpaRepository<BookingDetail, BookingDetailId> {

  List<BookingDetail> findAllByBookingCardBookingId(Integer bookingId);


}
