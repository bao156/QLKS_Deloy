package qlks_hdv.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import qlks_hdv.entity.Role;
import qlks_hdv.entity.User;
import qlks_hdv.exception.ConflictException;
import qlks_hdv.exception.NotFoundException;
import qlks_hdv.mapper.UserMapper;
import qlks_hdv.repository.RoleRepository;
import qlks_hdv.repository.UserRepository;
import qlks_hdv.request.CreateUserRequest;
import qlks_hdv.request.UpdateUserRequest;
import qlks_hdv.response.GetUserResponse;
import qlks_hdv.service.IUserService;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  private final UserMapper userMapper;

  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public void createUser(CreateUserRequest createUserRequest) {
    if (userRepository.existsByUsername(createUserRequest.getUsername())) {
      throw new ConflictException("username-already-exist");
    }
    Role roles = roleRepository.findOneByRoleName(createUserRequest.getRoleName())
        .orElseThrow(() -> new NotFoundException("role-not found"));

    User user = userMapper.mapToUser(createUserRequest, roles, passwordEncoder.encode(
        createUserRequest.getPassword()));
    userRepository.save(user);
  }

  @Override
  @Transactional
  public void updateUser(UpdateUserRequest updateUserRequest, String username) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new NotFoundException("user-not-found"));
    user = userMapper.mapToUser(updateUserRequest, user);
    userRepository.save(user);
  }

  @Override
  @Transactional
  public void deleteUser(String username) {

    if (!userRepository.existsByUsername(username)) {
      throw new NotFoundException("user-not-found");
    }
    userRepository.deleteById(username);
  }

  @Override
  public GetUserResponse getUserByUsername(String username) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new NotFoundException("user-not-found"));
    return userMapper.mapToGetUserResponse(user,
        roleRepository.findById(user.getRoles().getId()).get().getRoleName());
  }

  @Override
  public List<GetUserResponse> getAllUsers() {
    return userRepository.findAll().stream()
        .map(user -> userMapper.mapToGetUserResponse(user, user.getRoles().getRoleName())).collect(
            Collectors.toList());
  }


}
