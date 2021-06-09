package qlks_hdv.controller;


import java.util.HashMap;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qlks_hdv.request.CreateBookingCardRequest;
import qlks_hdv.request.RevenueAtDate;
import qlks_hdv.response.GetBookingCardReponse;
import qlks_hdv.service.impl.BookingCardService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("/api/bookingCards")
@RestController
public class BookingCardController {

  private final BookingCardService bookingCardService;

  @PostMapping
  public ResponseEntity<Void> createBookingCard(
      @Valid @RequestBody CreateBookingCardRequest createBookingCardRequest) {
    bookingCardService.createBookingCard(createBookingCardRequest);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{username}")
  public ResponseEntity<List<GetBookingCardReponse>> getAllBookingCardsByUsername(
      @PathVariable("username") String username) {

    return ResponseEntity.ok().body(bookingCardService.getAllBookingCardsByUsername(username));
  }

  @PutMapping("{bookingId}")
  public ResponseEntity<Void> disableBookingCard(@PathVariable("bookingId") Integer bookingId) {
    bookingCardService.disableBookingCard(bookingId);
    return ResponseEntity.ok().build();
  }

  @GetMapping
  ResponseEntity<HashMap<Integer,Integer>> getRevenue(int year) {
    return ResponseEntity.ok(bookingCardService.getRevenueAtDate(year));
  }

}
