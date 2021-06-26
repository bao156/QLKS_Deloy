package qlks_hdv.request;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class CreateRentingDetailRequest {

  private Integer bookingCardId;
  private String roomCode;
  private String rentAt;
  private String backAt;
  private String username;
  private Set<CreateCustomerRentingRequest> customers = new HashSet<>();
}
