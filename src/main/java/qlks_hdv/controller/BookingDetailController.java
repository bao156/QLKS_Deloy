package qlks_hdv.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qlks_hdv.request.CreateBookingDetailRequest;
import qlks_hdv.response.GetBookingDetailResponse;
import qlks_hdv.service.impl.BookingDetailService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("/api/bookingDetail")
@RestController
public class BookingDetailController {

  private final BookingDetailService bookingDetailService;

  @PostMapping
  public ResponseEntity<Void> addBookingDetail(
      @Valid @RequestBody CreateBookingDetailRequest createBookingDetailRequest) {
    bookingDetailService.addBookingDetail(createBookingDetailRequest);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{username}/{bookingId}")
  public ResponseEntity<List<GetBookingDetailResponse>> getBookingDetails(
      @PathVariable("bookingId") Integer bookingId, @PathVariable("username") String username) {
    return ResponseEntity.ok()
        .body(bookingDetailService.getBookingDetailByBookingCardId(bookingId, username));
  }

}
