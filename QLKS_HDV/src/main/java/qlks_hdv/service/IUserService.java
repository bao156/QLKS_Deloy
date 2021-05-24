package qlks_hdv.service;

import qlks_hdv.request.CreateUserRequest;
import qlks_hdv.request.UpdateUserRequest;

public interface IUserService {

  void createUser(CreateUserRequest createUserRequest);

  void updateUser(UpdateUserRequest updateUserRequest);

  void deleteUser(String[] username);
}
