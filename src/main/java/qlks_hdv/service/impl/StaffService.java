package qlks_hdv.service.impl;

import javax.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qlks_hdv.entity.Staff;
import qlks_hdv.entity.User;
import qlks_hdv.exception.ConflictException;
import qlks_hdv.exception.NotFoundException;
import qlks_hdv.mapper.StaffMapper;
import qlks_hdv.repository.StaffRepository;
import qlks_hdv.repository.UserRepository;
import qlks_hdv.request.CreateStaffRequest;
import qlks_hdv.request.UpdateStaffRequest;
import qlks_hdv.response.GetStaffResponse;
import qlks_hdv.service.IStaffService;

@Data
@RequiredArgsConstructor
@Service
public class StaffService implements IStaffService {

  private final StaffRepository staffRepository;

  private final UserRepository userRepository;

  private final StaffMapper staffMapper;


  @Override
  @Transactional
  public void createStaff(CreateStaffRequest createStaffRequest) {
    if (staffRepository.existsByPhone(createStaffRequest.getPhone())) {
      throw new ConflictException("phone-number-is-used");
    }
    if (staffRepository.existsByEmail(createStaffRequest.getEmail())) {
      throw new ConflictException("email-is-used");
    }

    User user = userRepository.findByUsername(createStaffRequest.getUsername())
        .orElseThrow(() -> new NotFoundException("user-not-found"));
    Staff staff = staffMapper.mapToStaff(createStaffRequest, user);
    staffRepository.save(staff);

  }

  @Override
  @Transactional
  public void updateStaff(UpdateStaffRequest updateStaffRequest, Integer staffId) {

    Staff staff = staffRepository.findById(staffId)
        .orElseThrow(() -> new NotFoundException("staff-not-found"));

    if (staffRepository.existsByPhone(updateStaffRequest.getPhone())
        && !updateStaffRequest.getPhone().equals(staff.getPhone())) {
      throw new ConflictException("phone-number-is-used");
    }
    if (staffRepository.existsByEmail(updateStaffRequest.getEmail())
        && !updateStaffRequest.getEmail().equals(staff.getEmail())
    ) {
      throw new ConflictException("email-is-used");
    }

    staff = staffMapper.mapToStaff(updateStaffRequest, staff);
    staffRepository.save(staff);
  }

  @Override
  @Transactional
  public void deleteStaff(Integer staffId) {
    if (!staffRepository.existsById(staffId)) {
      throw new NotFoundException("user-not-found");
    }
    staffRepository.deleteById(staffId);
  }

  @Override
  public GetStaffResponse getGetStaffResponse(Integer staffId) {

    Staff staff = staffRepository.findById(staffId)
        .orElseThrow(() -> new NotFoundException("staff-not-found"));
    return staffMapper.mapToGetStaffResponse(staff);
  }

}
