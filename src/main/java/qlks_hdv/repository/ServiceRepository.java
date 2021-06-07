package qlks_hdv.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.Services;

public interface ServiceRepository extends JpaRepository<Services, Integer> {

  Optional<Services> findByServiceName(String serviceName);

  Boolean existsByServiceName(String serviceName);
}
