package qlks_hdv.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qlks_hdv.entity.User;
import qlks_hdv.repository.RoleRepository;
import qlks_hdv.repository.UserRepository;
import qlks_hdv.request.CreateUserRequest;
import qlks_hdv.request.UpdateUserRequest;
import qlks_hdv.service.IUserService;


@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

  private final UserRepository userRepo;
  private final RoleRepository roleRepo;

  @Override
  public void createUser(CreateUserRequest createUserRequest) {
    User users = new User();
    //userUpdate=userRepo.findOne(userDTO.getUsername());
//    if (userUpdate != null) {
//      users = userUpdate;
//      users.setPassword(userDTO.getPassword());
//      userDTO.setRoleName(users.getRole().getRoleName());
//    } else {
//      Roles roles = roleRepo.findOneByRoleName(userDTO.getRoleName());
//      /// ĐỂ SET ROLE Ở TRÊN CONVERT LÀ ĂN LX
//      users = convert.toEntity(userDTO);
//      users.setRole(roles);
//    }
    userRepo.save(users);
  }

  public void updateUser(UpdateUserRequest updateUserRequest) {
    User users = new User();
    User userUpdate = new User();
    //userUpdate=userRepo.findOne(userDTO.getUsername());
//    if (userUpdate != null) {
//      users = userUpdate;
//      users.setPassword(userDTO.getPassword());
//      userDTO.setRoleName(users.getRole().getRoleName());
//    } else {
//      Roles roles = roleRepo.findOneByRoleName(userDTO.getRoleName());
//      /// ĐỂ SET ROLE Ở TRÊN CONVERT LÀ ĂN LX
//      users = convert.toEntity(userDTO);
//      users.setRole(roles);
//    }
    userRepo.save(users);
  }


  @Override
  public void deleteUser(String[] username) {
    for (String i : username) {
      //userRepo.delete(i);
    }
  }

}
