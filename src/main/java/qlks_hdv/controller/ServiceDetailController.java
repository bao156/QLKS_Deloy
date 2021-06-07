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
import qlks_hdv.request.CreateServiceDetailRequest;
import qlks_hdv.response.GetServiceDetailResponse;
import qlks_hdv.service.impl.ServiceDetailService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/serviceDetail")
@RequiredArgsConstructor
public class ServiceDetailController {

  private final ServiceDetailService serviceDetailService;

  @PostMapping
  public ResponseEntity<Void> addServiceDetail(
      @Valid @RequestBody CreateServiceDetailRequest createServiceDetailRequest) {
    serviceDetailService.addServiceDetail(createServiceDetailRequest);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{username}/{bookingId}")
  public ResponseEntity<List<GetServiceDetailResponse>> getServiceDetails(
      @Valid @PathVariable("bookingId") Integer bookingId,
      @PathVariable("username") String username) {
    return ResponseEntity.ok()
        .body(serviceDetailService.getServiceDetailResponseList(bookingId, username));
  }
}
