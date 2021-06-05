package qlks_hdv.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qlks_hdv.entity.Price;
import qlks_hdv.entity.RoomType;
import qlks_hdv.entity.compositekey.PriceId;
import qlks_hdv.mapper.RoomTypeMapper;
import qlks_hdv.repository.PricesRepository;
import qlks_hdv.repository.RoomTypeRepository;
import qlks_hdv.response.GetRoomTypeWithPriceResponse;

@Service
@RequiredArgsConstructor
public class RoomTypeService {

  private final RoomTypeRepository roomTypeRepository;
  private final PricesRepository pricesRepository;
  private final RoomTypeMapper roomTypeMapper;


  public List<GetRoomTypeWithPriceResponse> getRoomTypesByDate(Integer date) {

    List<RoomType> roomTypeList = roomTypeRepository.findAll();
    boolean isWeekend = (date == 7 || date == 1) ? true : false;

    return roomTypeList.stream()
        .map(roomType -> {
          PriceId priceId = new PriceId(roomType.getId(), isWeekend);
          Price price = pricesRepository.getOne(priceId);
          return roomTypeMapper.mapToGetRoomTypeWithPriceResponse(roomType, price.getPrice());
        }).collect(Collectors.toList());

  }
}
