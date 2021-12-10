package qlks_hdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.Services;

public interface ServiceRepository extends JpaRepository<Services, Integer> {


  Boolean existsByServiceName(String serviceName);
}
