package qlks_hdv.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateRoomRequest {

  @NotBlank(message = "cannot-be-empty")
  private String description;

  @NotBlank(message = "cannot-be-empty")
  private String image;

}
