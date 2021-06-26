package qlks_hdv.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateCustomerRentingRequest {

  @NotBlank(message = "cannot-be-empty")
  private String firstName;

  @NotBlank(message = "cannot-be-empty")
  private String lastName;

  @NotBlank(message = "cannot-be-empty")
  @Length(min = 9, max = 10, message = "length-must-be-9-or-10")
  private String CMND;

}
