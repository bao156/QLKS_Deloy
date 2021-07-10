package qlks_hdv.request;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SigninRequest {

  @NotEmpty(message = "cannot-be-empty")
  private String username;

  @NotEmpty(message = "cannot-be-empty")
  private String password;

}