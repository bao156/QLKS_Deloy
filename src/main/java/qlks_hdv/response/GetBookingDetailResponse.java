package qlks_hdv.response;

import lombok.Data;

@Data
public class GetBookingDetailResponse {

  private String nameType;

  private String recieveAt;

  private String backAt;

  private Integer amount;

  private Integer price;

}