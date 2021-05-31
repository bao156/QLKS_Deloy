package qlks_hdv.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

  Optional<Staff> findByUserUsername(String username);

  Optional<Staff> findByPhone(String phoneNumber);

  Optional<Staff> findByEmail(String email);

  Optional<Staff> findById(Integer id);

  Boolean existsByPhone(String phoneNumber);

  Boolean existsByEmail(String email);

  Optional<List<Staff>> getAllBy();


}
