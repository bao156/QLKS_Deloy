package qlks_hdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
