package qlks_hdv.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUserRequest {

  @NotBlank(message = "cannot-be-empty")
  private String password;

}
