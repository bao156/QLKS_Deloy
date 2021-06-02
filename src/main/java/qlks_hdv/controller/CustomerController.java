package qlks_hdv.controller;

import java.util.List;
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
import qlks_hdv.request.CreateCustomerRequest;
import qlks_hdv.request.UpdateCustomerRequest;
import qlks_hdv.response.GetCustomerResponse;
import qlks_hdv.service.impl.CustomerService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping
  public ResponseEntity<Void> createCustomer(
      @Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
    customerService.createCustomer(createCustomerRequest);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{customerId}")
  public ResponseEntity<Void> updateCustomer(@PathVariable("customerId") Integer customerId,
      @Valid @RequestBody UpdateCustomerRequest updateCustomerRequest) {
    customerService.updateCustomer(updateCustomerRequest, customerId);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{customerId}")
  public ResponseEntity<GetCustomerResponse> getCustomerByCustomerId(
      @PathVariable("customerId") String username) {
    return ResponseEntity.ok().body(customerService.getCustomerByUsername(username));
  }

  @DeleteMapping("/{customerId}")
  public ResponseEntity<GetCustomerResponse> deleteCustomer(
      @PathVariable("customerId") Integer customerId) {
    customerService.deleteCustomer(customerId);
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<List<GetCustomerResponse>> getCustomerResponseList() {
    return ResponseEntity.ok().body(customerService.getCustomerResponseList());
  }


}
