package qlks_hdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.RoomType;

public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {

  RoomType findOneByNumberOfBed(Integer number);
}
