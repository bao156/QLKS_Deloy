package qlks_hdv.response;

import lombok.Data;

@Data
public class GetRoomTypeWithPriceResponse {

  private Integer id;

  private Integer numberOfBed;

  private String name;

  private String desciption;

  private Integer price;

}
