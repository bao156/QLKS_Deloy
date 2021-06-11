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
      @Mapping(target = "price", ignore = true),
      @Mapping(target = "bookingCard", source = "bookingCard"),
      @Mapping(target = "type", source = "roomType")
  })
  BookingDetail mapToBookingDetail(CreateBookingDetailRequest createBookingDetailRequest,
      BookingCard bookingCard, RoomType roomType);

  @Mappings({
      @Mapping(target = "bookingId", source = "bookingDetail.bookingCard.bookingId"),
      @Mapping(target = "nameType", source = "bookingDetail.type.name"),
      @Mapping(target = "typeId", source = "bookingDetail.type.id"),
      @Mapping(target = "numberOfBed", source = "bookingDetail.type.numberOfBed")
  })
  GetBookingDetailResponse mapToGetBookingDetailResponse(BookingDetail bookingDetail);
}
