package qlks_hdv.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.RoomType;

public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {

  Optional<RoomType> findOneByNumberOfBed(Integer number);
}
