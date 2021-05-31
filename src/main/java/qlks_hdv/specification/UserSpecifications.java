package qlks_hdv.specification;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import qlks_hdv.entity.Role;
import qlks_hdv.entity.User;

public final class UserSpecifications {

  public static Specification<User> getFilter(UserCriteria userCriteria) {
    Specification<User> specification =
        Specification.where(null);
    if (userCriteria.getUsername() != null) {
      specification = specification.and(belongsToUser(userCriteria.getUsername()));
    }
    if (userCriteria.getPassword() != null) {
      specification = specification.and(belongsToPassword(userCriteria.getPassword()));
    }
    if (userCriteria.getRoles() != null) {
      specification = specification.and(roleIn(userCriteria.getRoles()));
    }
    return specification;
  }

  static Specification<User> belongsToUser(String username) {
    return (user, cq, cb) -> cb.equal(user.get("username"), username);
  }

  static Specification<User> belongsToPassword(String password) {
    return (user, cq, cb) -> cb.equal(user.get("password"), password);
  }

  static Specification<User> roleIn(List<Role> roles) {
    return (user, cq, cb) -> user.get("roles").in(roles);
  }

}
