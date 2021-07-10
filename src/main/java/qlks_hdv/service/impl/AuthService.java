package qlks_hdv.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import qlks_hdv.exception.BadRequestException;
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

    Boolean isValid = true;
    if (userRepository.existsByUsernameAndPassword(signinRequest.getUsername(),
        passwordEncoder.encode(signinRequest.getPassword()))) {
      throw new BadRequestException("username-or-password-is-incorrect");
    }
    return isValid;

  }


}
