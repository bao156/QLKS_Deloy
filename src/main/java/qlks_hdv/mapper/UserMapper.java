package qlks_hdv.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import qlks_hdv.entity.Role;
import qlks_hdv.entity.User;
import qlks_hdv.request.CreateUserRequest;
import qlks_hdv.request.UpdateUserRequest;
import qlks_hdv.response.GetUserResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {

  @Mapping(target = "roles", source = "role")
  @Mapping(target = "password", source = "encodePassword")
  @Mapping(target = "status", expression = "java(1)")
  public abstract User mapToUser(CreateUserRequest createUserRequest, Role role,
      String encodePassword);

  public abstract User mapToUser(UpdateUserRequest updateUserRequest, @MappingTarget User user);

  @Mapping(target = "roleName", source = "roleName")
  public abstract GetUserResponse mapToGetUserResponse(User user, String roleName);

}
