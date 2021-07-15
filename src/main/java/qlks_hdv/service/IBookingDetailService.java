package qlks_hdv.service;

import java.util.Date;
import java.util.List;
import qlks_hdv.entity.compositekey.BookingDetailId;
import qlks_hdv.request.CreateBookingDetailRequest;
import qlks_hdv.response.GetBookingDetailResponse;
import qlks_hdv.response.GetRoomTypeWithPriceResponse;

public interface IBookingDetailService {

  void addBookingDetail(CreateBookingDetailRequest createBookingDetailRequest);

  List<GetBookingDetailResponse> getBookingDetailByBookingCardId(Integer bookingId,
      String username);

  List<GetBookingDetailResponse> getBookingDetailByBookingId(Integer bookingId);

  Integer getPriceOfARoom(String recieveDate, String backDate, Integer typeId);

  List<GetRoomTypeWithPriceResponse> getRoomTypeBookingRank();

  void deleteDetailBooking(BookingDetailId bookingDetailId);

  String downToDate(String date);

  List<Date> getDatesFromTo(String dateFrom, String dateTo);

  Date changeStringToDate(String date);

}
