package qlks_hdv.request;

import lombok.Data;

@Data
public class CreateServiceDetailRequest {

  private String username;

  private String nameService;

  private Integer quantity;

  private Integer price;
}
