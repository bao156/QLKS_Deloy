package qlks_hdv.service;

import java.util.List;
import qlks_hdv.request.CreateUserRequest;
import qlks_hdv.request.UpdateUserRequest;
import qlks_hdv.response.GetUserResponse;

public interface IUserService {

  void createUser(CreateUserRequest createUserRequest);

  void updateUser(UpdateUserRequest updateUserRequest, String username);

  void deleteUser(String username);

  GetUserResponse getUserByUsername(String username);

  List<GetUserResponse> getAllUsers();

}
