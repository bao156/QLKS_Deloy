package qlks_hdv.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import qlks_hdv.entity.User;
import qlks_hdv.exception.NotFoundException;
import qlks_hdv.mapper.UserMapper;
import qlks_hdv.repository.RoleRepository;
import qlks_hdv.repository.UserRepository;
import qlks_hdv.request.SigninRequest;
import qlks_hdv.service.IAuthService;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  private final UserMapper userMapper;

  private final PasswordEncoder passwordEncoder;

  @Override
  public Boolean authenticateUser(SigninRequest signinRequest) {

    User user = userRepository.findByUsername(signinRequest.getUsername())
        .orElseThrow(() -> new NotFoundException(("username-is-incorrect")));
    if (!passwordEncoder.matches(signinRequest.getPassword(), user.getPassword())) {
      throw new NotFoundException("password-is-incorrect");
    }
    return true;


  }


}
