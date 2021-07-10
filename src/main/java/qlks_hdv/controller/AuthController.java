package qlks_hdv.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qlks_hdv.request.SigninRequest;
import qlks_hdv.service.IAuthService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {

  private final IAuthService authService;

  @PostMapping("/signin")
  public ResponseEntity<Boolean> authenticateUser(
      @Valid @RequestBody SigninRequest signinRequest) {
    Boolean response = authService.authenticateUser(signinRequest);
    return ResponseEntity.ok(response);
  }

}
