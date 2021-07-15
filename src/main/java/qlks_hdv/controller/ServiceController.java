package qlks_hdv.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qlks_hdv.request.CreateServiceRequest;
import qlks_hdv.request.UpdateServiceRequest;
import qlks_hdv.response.GetServiceResponse;
import qlks_hdv.service.impl.ServicesService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("api/services")
@RestController
public class ServiceController {

  private final ServicesService servicesService;

  @PostMapping
  public ResponseEntity<Void> createService(
      @Valid @RequestBody CreateServiceRequest createServiceRequest) {
    servicesService.createService(createServiceRequest);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{serviceId}")
  public ResponseEntity<Void> updateService(
      @Valid @RequestBody UpdateServiceRequest updateServiceRequest,
      @PathVariable("serviceId") Integer serviceId, HttpServletResponse response) {
    servicesService.updateService(updateServiceRequest, serviceId);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{serviceId}")
  public ResponseEntity<Void> deleteService(@PathVariable("serviceId") Integer serviceId) {
    servicesService.deleteService(serviceId);
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<List<GetServiceResponse>> getServices() {
    return ResponseEntity.ok().body(servicesService.getServices());
  }
}
