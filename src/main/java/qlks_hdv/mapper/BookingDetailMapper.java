package qlks_hdv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import qlks_hdv.entity.BookingCard;
import qlks_hdv.entity.BookingDetail;
import qlks_hdv.entity.RoomType;
import qlks_hdv.request.CreateBookingDetailRequest;
import qlks_hdv.response.GetBookingDetailResponse;

@Mapper(componentModel = "spring")
public interface BookingDetailMapper {

  @Mappings({
      @Mapping(target = "price", source = "createBookingDetailRequest.price")
  })
  BookingDetail mapToBookingDetail(CreateBookingDetailRequest createBookingDetailRequest,
      BookingCard bookingCard, RoomType roomType);

  @Mappings({
      @Mapping(target = "nameType", source = "bookingDetail.type.name"),
      @Mapping(target = "numberOfBed", source = "bookingDetail.type.numberOfBed")
  })
  GetBookingDetailResponse mapToGetBookingDetailResponse(BookingDetail bookingDetail);
}
