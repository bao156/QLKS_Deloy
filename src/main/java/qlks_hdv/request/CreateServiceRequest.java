package qlks_hdv.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateServiceRequest {

  @NotBlank(message = "cannot-be-empty")
  private String serviceName;

  private Integer price;

  private String image;

}
