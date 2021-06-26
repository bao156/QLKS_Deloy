package qlks_hdv.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

  Optional<Customer> findByUserUsername(String username);

  Boolean existsByUserUsername(String username);

  Optional<List<Customer>> findAllBy();

  Optional<Customer> findByCMND(String cmnd);

  Boolean existsByCMND(String cmnd);

}
