package qlks_hdv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import qlks_hdv.entity.BookingCard;
import qlks_hdv.entity.RentingDetail;
import qlks_hdv.entity.Room;
import qlks_hdv.entity.Staff;
import qlks_hdv.request.CreateRentingDetailRequest;

@Mapper(componentModel = "spring")
public interface RentingMapper {

  @Mappings({
      @Mapping(target = "bookingCard", source = "bookingCard"),
      @Mapping(target = "room", source = "room"),
      @Mapping(target = "staff", source = "staff"),
      @Mapping(target = "customers", ignore = true)
  })
  RentingDetail mapToRenting(CreateRentingDetailRequest createRentingDetailRequest,
      BookingCard bookingCard,
      Room room, Staff staff);
}
