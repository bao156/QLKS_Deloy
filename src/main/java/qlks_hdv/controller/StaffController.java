package qlks_hdv.controller;


import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qlks_hdv.request.CreateStaffRequest;
import qlks_hdv.request.UpdateStaffRequest;
import qlks_hdv.response.GetStaffResponse;
import qlks_hdv.service.impl.StaffService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/staffs")
public class StaffController {


  private final StaffService staffService;

  @PostMapping
  public ResponseEntity<Void> createStaff(
      @Valid @RequestBody CreateStaffRequest createStaffRequest) {
    staffService.createStaff(createStaffRequest);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{staffId}")
  public ResponseEntity<Void> updateStaff(
      @Valid @RequestBody UpdateStaffRequest updateStaffRequest,
      @PathVariable("staffId") Integer staffId) {
    staffService.updateStaff(updateStaffRequest, staffId);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{staffId}")
  public ResponseEntity<Void> deleteStaff(@PathVariable("staffId") Integer staffId) {
    staffService.deleteStaff(staffId);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{staffId}")
  public ResponseEntity<GetStaffResponse> getStaffResponse(
      @PathVariable("staffId") Integer staffId) {
    return ResponseEntity.ok().body(staffService.getGetStaffResponse(staffId));
  }

}
