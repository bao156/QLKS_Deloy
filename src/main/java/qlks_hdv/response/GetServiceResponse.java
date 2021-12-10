package qlks_hdv.response;

import lombok.Data;

@Data
public class GetServiceResponse {

  private String serviceName;

  private Integer price;

  private String image;
}
