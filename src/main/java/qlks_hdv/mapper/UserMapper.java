package qlks_hdv.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import qlks_hdv.entity.Role;
import qlks_hdv.entity.User;
import qlks_hdv.request.CreateUserRequest;
import qlks_hdv.request.UpdateUserRequest;
import qlks_hdv.response.GetUserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "roles", source = "role")
  @Mapping(target = "password", source = "encodePassword")
  User mapToUser(CreateUserRequest createUserRequest, Role role, String encodePassword);

  User mapToUser(UpdateUserRequest updateUserRequest, @MappingTarget User user);

  @Mapping(target = "roleName", source = "roleName")
  GetUserResponse mapToGetUserResponse(User user, String roleName);

}
