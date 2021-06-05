package qlks_hdv.response;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GetRoomResponse {

  private String roomCode;

  private Integer numberOfBed;

  private String description;

  private String image;

}
