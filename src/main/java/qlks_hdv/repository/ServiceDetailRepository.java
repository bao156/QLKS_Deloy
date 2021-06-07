package qlks_hdv.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.ServiceDetail;
import qlks_hdv.entity.compositekey.ServiceDetailId;

public interface ServiceDetailRepository extends JpaRepository<ServiceDetail, ServiceDetailId> {

  List<ServiceDetail> findAllByBookingCardBookingId(Integer bookingId);

}
