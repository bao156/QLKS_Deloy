package qlks_hdv.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qlks_hdv.request.CreateRentingDetailRequest;
import qlks_hdv.service.impl.RentingDetailService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("/api/rentingDetail")
@RestController
public class RentingDetailController {

  private final RentingDetailService rentingDetailService;

  @PostMapping
  public ResponseEntity<Void> addRentingDetail(
      @Valid @RequestBody CreateRentingDetailRequest createRentingDetailRequest) {
    rentingDetailService.createRentingDetail(createRentingDetailRequest);
    return ResponseEntity.ok().build();
  }


}
