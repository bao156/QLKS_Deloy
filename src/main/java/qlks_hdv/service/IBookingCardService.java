package qlks_hdv.service;

import java.util.HashMap;
import java.util.List;
import qlks_hdv.entity.BookingCard;
import qlks_hdv.request.CreateBookingCardRequest;
import qlks_hdv.response.GetBookingCardReponse;

public interface IBookingCardService {

  void createBookingCard(CreateBookingCardRequest createBookingCardRequest);

  List<GetBookingCardReponse> getAllBookingCardsByUsername(String username);

  void updatePriceOfBookingCard(int bookingId);

  void updateCompleteDateOnBookingCard(int bookingId);

  void disableBookingCard(Integer bookingId);

  HashMap<Integer, Integer> getRevenueAtDate(int year);

  List<Integer> getPriceCalcute(int year, BookingCard bookingCard);
}
