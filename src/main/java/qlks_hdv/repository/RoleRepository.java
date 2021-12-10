package qlks_hdv.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

  Optional<Role> findOneByRoleName(String roleName);

  Boolean existsByRoleName(String roleName);

}
