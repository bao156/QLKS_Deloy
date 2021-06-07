package qlks_hdv.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class CreateBookingDetailRequest {

  private String username;

  private Integer typeId;

  @DateTimeFormat(pattern = "yyyy/mm/dd")
  private String recieveAt;

  @DateTimeFormat(pattern = "yyyy/mm/dd")
  private String backAt;

  private Integer amount;

  private Integer price;
}
