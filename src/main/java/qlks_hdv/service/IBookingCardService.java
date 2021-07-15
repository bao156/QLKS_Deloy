package qlks_hdv.service;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import qlks_hdv.entity.BookingCard;
import qlks_hdv.request.CreateBookingCardRequest;
import qlks_hdv.response.GetBookingCardForPaymentReponse;
import qlks_hdv.response.GetBookingCardReponse;

public interface IBookingCardService {

  void createBookingCard(CreateBookingCardRequest createBookingCardRequest);

  List<GetBookingCardReponse> getAllBookingCardsByUsername(String username);

  List<GetBookingCardReponse> getAllBookingCardsInReservated();

  void updatePriceOfBookingCard(int bookingId);

  void updateCompleteDateOnBookingCard(int bookingId);

  void changeStatusBookingCard(Integer bookingId, Boolean isCancel);

  HashMap<Integer, Integer> getRevenueAtDate(int year);

  List<Integer> getPriceCalcute(int year, BookingCard bookingCard);

  GetBookingCardForPaymentReponse Payment(Integer bookingId, String username,
      HttpServletResponse response);
}
