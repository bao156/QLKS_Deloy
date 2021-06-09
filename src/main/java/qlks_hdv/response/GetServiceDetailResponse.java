package qlks_hdv.response;

import lombok.Data;

@Data
public class GetServiceDetailResponse {

  private Integer bookingId;

  private String nameService;

  private Integer quantity;

  private Integer price;

}
