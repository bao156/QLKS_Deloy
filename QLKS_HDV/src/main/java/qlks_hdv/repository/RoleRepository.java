package qlks_hdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer> {

  Roles findOneByRoleName(String roleName);
}
