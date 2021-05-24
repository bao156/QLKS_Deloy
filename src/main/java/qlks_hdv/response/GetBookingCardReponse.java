package qlks_hdv.response;

import lombok.Data;

@Data
public class GetBookingCardReponse {

  private Integer bookingId;

  private Integer price;

  private String status;

  private String username;

  private String lastName;

  private String phoneNumber;

  private String discountName;
}
