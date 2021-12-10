package qlks_hdv.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

@Data
public class CreateCustomerRequest {

  @NotBlank(message = "cannot-be-empty")
  private String firstName;

  @NotBlank(message = "cannot-be-empty")
  private String lastName;

  @NotBlank(message = "cannot-be-empty")
  @Size(min = 10, max = 10, message = "length-must-be-10-characters")
  private String phone;

  @Email(message = "not-valid")
  private String email;

  @NaturalId
  @NotBlank(message = "cannot-be-empty")
  private String username;

}
