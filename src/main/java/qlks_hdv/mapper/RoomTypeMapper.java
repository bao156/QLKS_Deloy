package qlks_hdv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import qlks_hdv.entity.RoomType;
import qlks_hdv.response.GetRoomTypeWithPriceResponse;

@Mapper(componentModel = "spring")
public interface RoomTypeMapper {

  @Mapping(target = "price", source = "price")
  GetRoomTypeWithPriceResponse mapToGetRoomTypeWithPriceResponse(RoomType roomType, Integer price);
}
