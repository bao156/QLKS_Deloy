package qlks_hdv.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.Room;

public interface RoomRepostiory extends JpaRepository<Room, String> {

  Boolean existsByRoomCode(String code);

  Optional<List<Room>> getAllByStatus(String status);


}
