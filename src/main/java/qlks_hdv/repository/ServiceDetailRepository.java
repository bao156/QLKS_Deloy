package qlks_hdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.ServiceDetail;
import qlks_hdv.entity.compositekey.ServiceDetailId;

public interface ServiceDetailRepository extends JpaRepository<ServiceDetail, ServiceDetailId> {

}
