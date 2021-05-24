package qlks_hdv.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import qlks_hdv.entity.Roles;
import qlks_hdv.entity.User;
import qlks_hdv.request.CreateUserRequest;
import qlks_hdv.request.UpdateUserRequest;

@Mapper(componentModel = "spring")
public interface UserMapper {
  @Mappings({
      @Mapping(target = "role", source = "role")
  })
  User mapBeforeCreate(CreateUserRequest createUserRequest, Roles role);

  User mapBeforeUpdate(UpdateUserRequest updateUserRequest, @MappingTarget User user);
}
