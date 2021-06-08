package qlks_hdv.response;

import lombok.Data;

@Data
public class GetStaffResponse {

  private Integer id;

  private String firstName;

  private String lastName;

  private String phone;

  private String email;

  private String address;

  private String username;
}
