package qlks_hdv.service;

import java.util.List;
import qlks_hdv.entity.RoomType;
import qlks_hdv.response.GetRoomResponseWithPrice;

public interface IRoomTypeService {

  List<GetRoomResponseWithPrice> getRoomTypesByDate(Integer date);

}
