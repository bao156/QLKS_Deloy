package qlks_hdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.Renting;
import qlks_hdv.entity.compositekey.RentingCompositeKey;

public interface RentingRepository extends JpaRepository<Renting, RentingCompositeKey>{

}
