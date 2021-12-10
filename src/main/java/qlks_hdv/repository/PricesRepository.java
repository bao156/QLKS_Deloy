package qlks_hdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.Price;
import qlks_hdv.entity.compositekey.PriceId;

public interface PricesRepository extends JpaRepository<Price, PriceId> {

}
