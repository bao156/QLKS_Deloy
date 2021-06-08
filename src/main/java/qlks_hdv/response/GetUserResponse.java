package qlks_hdv.response;

import lombok.Data;

@Data
public class GetUserResponse {

  private String username;

  private String password;

  private String roleName;

}
