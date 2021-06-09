package qlks_hdv.service;

import qlks_hdv.request.CreateRoomRequest;
import qlks_hdv.request.UpdateRoomRequest;
import qlks_hdv.response.GetRoomResponseWithPrice;

public interface IRoomService {

  void createaRoom(CreateRoomRequest createRoomRequest);

  void updateRoom(UpdateRoomRequest updateRoomRequest, String code);

  void deleteRoom(String code);

  GetRoomResponseWithPrice getRoomDetail(String roomCode);

}
