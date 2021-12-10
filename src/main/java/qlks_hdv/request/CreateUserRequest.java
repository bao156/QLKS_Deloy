package qlks_hdv.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserRequest {

  @NotBlank(message = "cannot-be-empty")
  private String username;

  @NotBlank(message = "cannot-be-empty")
  private String password;

  @NotBlank(message = "cannot-be-empty")
  private String roleName;

}
