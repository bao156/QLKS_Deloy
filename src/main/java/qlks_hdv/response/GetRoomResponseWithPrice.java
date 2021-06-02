package qlks_hdv.response;

import lombok.Data;

@Data
public class GetRoomResponseWithPrice {

  private String roomCode;

  private Integer numberOfBed;

  private String status;

  private String description;

  private String image;

  private Integer price;
}
