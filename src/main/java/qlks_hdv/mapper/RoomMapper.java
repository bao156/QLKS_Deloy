package qlks_hdv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import qlks_hdv.entity.Room;
import qlks_hdv.entity.RoomType;
import qlks_hdv.request.CreateRoomRequest;
import qlks_hdv.request.UpdateRoomRequest;
import qlks_hdv.response.GetRoomResponse;
import qlks_hdv.response.GetRoomResponseWithPrice;

@Mapper(componentModel = "spring")
public interface RoomMapper {

  @Mappings({
      @Mapping(target = "status", source = "status"),
      @Mapping(target = "type", source = "type")
  })
  Room mapToRoom(CreateRoomRequest createRoomRequest, String status, RoomType type);

  Room mapToRoom(UpdateRoomRequest updateRoomRequest, @MappingTarget Room room);

  @Mapping(target = "numberOfBed", source = "type.numberOfBed")
  GetRoomResponse mapToGetRoomResponse(Room room);

  @Mappings({
      @Mapping(target = "numberOfBed", source = " room.type.numberOfBed"),
      @Mapping(target = "price", source = "price")
  })
  GetRoomResponseWithPrice mapToGetRoomResponseWithPrice(Room room, Integer price);

}
