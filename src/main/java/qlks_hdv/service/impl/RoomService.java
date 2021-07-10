package qlks_hdv.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qlks_hdv.entity.Price;
import qlks_hdv.entity.Room;
import qlks_hdv.entity.RoomStatus;
import qlks_hdv.entity.RoomType;
import qlks_hdv.entity.compositekey.PriceId;
import qlks_hdv.exception.ConflictException;
import qlks_hdv.exception.NotFoundException;
import qlks_hdv.mapper.RoomMapper;
import qlks_hdv.repository.PricesRepository;
import qlks_hdv.repository.RoomRepostiory;
import qlks_hdv.repository.RoomTypeRepository;
import qlks_hdv.request.CreateRoomRequest;
import qlks_hdv.request.UpdateRoomRequest;
import qlks_hdv.response.GetRoomResponseWithPrice;
import qlks_hdv.service.IRoomService;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService {

  private final RoomRepostiory roomRepostiory;

  private final PricesRepository pricesRepository;

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

    Room room = roomMapper.mapToRoom(createRoomRequest, type, RoomStatus.Empty.name());
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

  public GetRoomResponseWithPrice getRoomDetail(String roomCode) {
    Room room = roomRepostiory.findById(roomCode)
        .orElseThrow(() -> new NotFoundException("room-not-exist"));

    PriceId priceId = new PriceId(room.getType().getId(), isWeekend());
    Price price = pricesRepository.getOne(priceId);
    return roomMapper
        .mapToGetRoomResponseWithPrice(room, price.getPrice());
  }

  @Override
  public List<GetRoomResponseWithPrice> getAllRooms() {

    return roomRepostiory.findAll().stream().map(room -> {
      PriceId priceId = new PriceId(room.getType().getId(), isWeekend());
      Price price = pricesRepository.getOne(priceId);
      return roomMapper
          .mapToGetRoomResponseWithPrice(room, price.getPrice());
    }).collect(Collectors.toList());

  }

  private static Boolean isWeekend() {
    Date now = new Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(now);
    int date = cal.get(Calendar.DAY_OF_WEEK);
    boolean isWeekend = (date == 7 || date == 1) ? true : false;
    return isWeekend;
  }
}
