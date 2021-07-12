package qlks_hdv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import qlks_hdv.entity.Staff;
import qlks_hdv.entity.User;
import qlks_hdv.request.CreateStaffRequest;
import qlks_hdv.request.UpdateStaffRequest;
import qlks_hdv.response.GetStaffResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class StaffMapper {

  @Mapping(target = "user", source = "user")
  @Mapping(target = "status", expression = "java(1)")
  public abstract Staff mapToStaff(CreateStaffRequest createStaffRequest, User user);

  public abstract Staff mapToStaff(UpdateStaffRequest updateCustomerRequest,
      @MappingTarget Staff staff);

  @Mapping(target = "username", source = "user.username")
  @Mapping(target = "roleName", source = "user.roles.roleName")
  public abstract GetStaffResponse mapToGetStaffResponse(Staff staff);


}
