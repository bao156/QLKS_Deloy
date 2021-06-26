package qlks_hdv.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Length;

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

  @NotBlank(message = "cannot-be-empty")
  @Length(min = 9, max = 10, message = "length-must-be-9-or-10")
  private String CMND;

  @NaturalId
  @NotBlank(message = "cannot-be-empty")
  private String username;

}
