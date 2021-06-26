package qlks_hdv.response;

import lombok.Data;

@Data
public class GetBookingCardForPaymentReponse {

  private Integer bookingId;

  private Integer serviceAmount;

  private Integer roomAmount;

  private String completeAt;

  private String username;

  private String firstName;

  private String CMND;

  private String lastName;

}
