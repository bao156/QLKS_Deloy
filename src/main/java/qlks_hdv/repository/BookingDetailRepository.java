package qlks_hdv.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.BookingDetail;
import qlks_hdv.entity.compositekey.BookingDetailId;

public interface BookingDetailRepository extends JpaRepository<BookingDetail, BookingDetailId> {

  List<BookingDetail> findAllByBookingCardBookingId(Integer bookingId);

  List<BookingDetail> findAllByBookingCardBookingId(Integer bookingId, Sort sort);

  List<BookingDetail> findAllByBackAtAfterAndTypeId(String backAt,
      Integer typeId);

  Optional<BookingDetail> findByBookingCardBookingIdAndTypeId(Integer bookingId, Integer typeId);


}
