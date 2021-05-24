package qlks_hdv.service;

import java.util.List;
import qlks_hdv.request.CreateRoomRequest;
import qlks_hdv.request.UpdateRoomRequest;
import qlks_hdv.response.GetRoomResponse;

public interface IRoomService {

  void createaRoom(CreateRoomRequest createRoomRequest);

  void updateRoom(UpdateRoomRequest updateRoomRequest, String code);

  void deleteRoom(String code);

  List<GetRoomResponse> getAllRoomsByStatus(String status);


}
