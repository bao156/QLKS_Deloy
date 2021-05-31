package qlks_hdv.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import qlks_hdv.entity.Room;

public interface RoomRepostiory extends PagingAndSortingRepository<Room, String> {

  Boolean existsByRoomCode(String code);

  Optional<List<Room>> getAllByStatus(String status);

  List<Room> findAllBy(Pageable pageable);

}
