package qlks_hdv.response;

import lombok.Data;

@Data
public class GetServiceDetailResponse {

  private Integer bookingId;

  private Integer serviceId;

  private String serviceName;

  private Integer quantity;

  private Integer price;

}
