package qlks_hdv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import qlks_hdv.entity.BookingCard;
import qlks_hdv.entity.Customer;
import qlks_hdv.entity.Discount;
import qlks_hdv.request.CreateBookingCardRequest;
import qlks_hdv.response.GetBookingCardForPaymentReponse;
import qlks_hdv.response.GetBookingCardReponse;

@Mapper(componentModel = "spring")
public interface BookingCardMapper {
  @Mappings({
      @Mapping(target = "price", source = "price"),
      @Mapping(target = "status", source = "status"),
      @Mapping(target = "customer", source = "customer"),
      @Mapping(target = "discount", source = "discount")
  })
  BookingCard mapToBookingCard(CreateBookingCardRequest createBookingCardRequest, Integer price,
      String status, Customer customer,
      Discount discount);

  @Mappings({
      @Mapping(target = "username", source = "customer.user.username"),
      @Mapping(target = "lastName", source = "customer.lastName"),
      @Mapping(target = "phoneNumber", source = "customer.phone"),
      @Mapping(target = "discountName", source = "discount.discountName")
  })
  GetBookingCardReponse mapToGetBookingCardReponse(BookingCard bookingCard);

  @Mappings({
      @Mapping(target = "username", source = "staffUser"),
      @Mapping(target = "serviceAmount", source = "serviceAmount"),
      @Mapping(target = "roomAmount", source = "roomAmount"),
      @Mapping(target = "firstName", source = "customer.firstName"),
      @Mapping(target = "lastName", source = "customer.lastName"),
      @Mapping(target = "CMND", source = "customer.CMND")

  })
  GetBookingCardForPaymentReponse mapTGetBookingCardForPaymentReponse(BookingCard bookingCard,
      Integer serviceAmount, Integer roomAmount, String staffUser,Customer customer);
}
