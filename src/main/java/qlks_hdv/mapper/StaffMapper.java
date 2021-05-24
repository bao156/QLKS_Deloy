package qlks_hdv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import qlks_hdv.entity.Staff;
import qlks_hdv.entity.User;
import qlks_hdv.request.CreateStaffRequest;
import qlks_hdv.request.UpdateCustomerRequest;
import qlks_hdv.request.UpdateStaffRequest;
import qlks_hdv.response.GetStaffResponse;

@Mapper(componentModel = "spring")
public interface StaffMapper {

  @Mapping(target = "user", source = "user")
  Staff mapToStaff(CreateStaffRequest createStaffRequest, User user);

  Staff mapToStaff(UpdateStaffRequest updateCustomerRequest, @MappingTarget Staff staff);

  @Mapping(target = "username", source = "user.username")
  GetStaffResponse mapToGetStaffResponse(Staff staff);

}
