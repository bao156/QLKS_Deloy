package qlks_hdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {

  Discount findByDiscountName(String discountName);

}
