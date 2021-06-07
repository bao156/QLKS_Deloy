package qlks_hdv.response;

import lombok.Data;

@Data
public class GetServiceDetailResponse {

  private String nameService;

  private Integer quantity;

  private Integer price;

}
