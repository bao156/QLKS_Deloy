package qlks_hdv.service;

import java.util.List;
import qlks_hdv.request.CreateBookingDetailRequest;
import qlks_hdv.response.GetBookingDetailResponse;

public interface IBookingDetailService {

  void addBookingDetail(CreateBookingDetailRequest createBookingDetailRequest);

  List<GetBookingDetailResponse> getBookingDetailByBookingCardId(Integer bookingId,
      String username);

  Integer getPriceOfARoom(String recieveDate, String backDate, Integer typeId);

}
