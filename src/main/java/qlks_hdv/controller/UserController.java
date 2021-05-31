package qlks_hdv.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qlks_hdv.request.CreateUserRequest;
import qlks_hdv.request.UpdateUserRequest;
import qlks_hdv.response.GetUserResponse;
import qlks_hdv.service.impl.UserService;
import qlks_hdv.specification.UserCriteria;

//Required là đc rồi
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<Void> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
    userService.createUser(createUserRequest);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{username}")
  public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {
    userService.deleteUser(username);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{username}")
  public ResponseEntity<Void> updateUser(@PathVariable("username") String username,
      @Valid @RequestBody UpdateUserRequest updateUserRequest) {
    userService.updateUser(updateUserRequest, username);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{username}")
  public ResponseEntity<GetUserResponse> getUserByUsername(
      @PathVariable("username") String username) {
    return ResponseEntity.ok().body(userService.getUserByUsername(username));
  }

  @GetMapping
  public ResponseEntity<List<GetUserResponse>> getUserByUsername(@Valid UserCriteria userCriteria) {
    return ResponseEntity.ok().body(userService.getAll(userCriteria));
  }

}
