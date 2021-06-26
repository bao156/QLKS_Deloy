package qlks_hdv.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.RentingDetail;
import qlks_hdv.entity.compositekey.RentingDetailId;

public interface RentingDetailRepository extends JpaRepository<RentingDetail, RentingDetailId> {

  List<RentingDetail> findAllByBookingCardBookingIdAndRoomTypeId(Integer bookingcardId,
      Integer roomtypeId);

  List<RentingDetail> findAllByBookingCardBookingId(Integer bookingCardId);
}
