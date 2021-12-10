package qlks_hdv.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

@Data
public class CreateRoomRequest {

  @NotBlank(message = "cannot-be-empty")
  private String roomCode;

  private Integer numberOfBed;

  @NotBlank(message = "cannot-be-empty")
  private String description;

  @NotBlank(message = "cannot-be-empty")
  private String image;

}
