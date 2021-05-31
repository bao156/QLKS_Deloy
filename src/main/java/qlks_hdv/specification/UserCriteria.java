package qlks_hdv.specification;

import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import qlks_hdv.entity.Role;

@Data
@RequiredArgsConstructor
public class UserCriteria {

  private String username;
  private String password;
  private List<Role> roles;

}
