package qlks_hdv.response;

import lombok.Data;

@Data
public class GetCustomerResponse {

  Integer id;

  private String firstName;

  private String lastName;

  private String phone;

  private String email;

  private String username;

  private String roleName;

  private String cmnd;

}
