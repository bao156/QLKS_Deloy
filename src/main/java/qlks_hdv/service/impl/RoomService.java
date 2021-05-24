package qlks_hdv.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qlks_hdv.entity.Room;
import qlks_hdv.entity.RoomType;
import qlks_hdv.exception.ConflictException;
import qlks_hdv.exception.NotFoundException;
import qlks_hdv.mapper.RoomMapper;
import qlks_hdv.repository.RoomRepostiory;
import qlks_hdv.repository.RoomTypeRepository;
import qlks_hdv.request.CreateRoomRequest;
import qlks_hdv.request.UpdateRoomRequest;
import qlks_hdv.response.GetRoomResponse;
import qlks_hdv.service.IRoomService;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService {

  private final RoomRepostiory roomRepostiory;

  private final RoomMapper roomMapper;

  private final RoomTypeRepository roomTypeRepository;


  @Override
  @Transactional
  public void createaRoom(CreateRoomRequest createRoomRequest) {
    if (roomRepostiory.existsByRoomCode(createRoomRequest.getRoomCode())) {
      throw new ConflictException("room-code-already-exist");
    }

    RoomType type = roomTypeRepository.findOneByNumberOfBed(createRoomRequest.getNumberOfBed())
        .orElseThrow(() -> new NotFoundException("type-not-found"));

    Room room = roomMapper.mapToRoom(createRoomRequest, "Empty", type);
    roomRepostiory.save(room);

  }

  @Override
  @Transactional
  public void updateRoom(UpdateRoomRequest updateRoomRequest, String roomCode) {
    Room room = roomRepostiory.findById(roomCode)
        .orElseThrow(() -> new NotFoundException("room-not-exist"));
    room = roomMapper.mapToRoom(updateRoomRequest, room);
    roomRepostiory.save(room);
  }

  @Override
  @Transactional
  public void deleteRoom(String roomCode) {
    Room room = roomRepostiory.findById(roomCode)
        .orElseThrow(() -> new NotFoundException("room-not-exist"));
    roomRepostiory.deleteById(roomCode);
  }

  @Override
  public List<GetRoomResponse> getAllRoomsByStatus(String status) {
    List<Room> roomList = roomRepostiory.getAllByStatus(status)
        .orElseThrow(() -> new NotFoundException("no-room-is-empty"));

    List<GetRoomResponse> getRoomReponseList = roomList.stream()
        .map(develop -> roomMapper.mapToGetRoomResponse(develop)).collect(Collectors.toList());
    return getRoomReponseList;
  }
}
