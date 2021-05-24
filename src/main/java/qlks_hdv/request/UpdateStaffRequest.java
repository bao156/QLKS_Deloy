package qlks_hdv.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateStaffRequest {

  @NotBlank(message = "cannot-be-empty")
  private String firstName;

  @NotBlank(message = "cannot-be-empty")
  private String lastName;

  @Size(min = 10, max = 10, message = "length-must-be-10-characters")
  private String phone;

  @Email
  private String email;

  @NotBlank(message = "cannot-be-empty")
  private String address;

}
