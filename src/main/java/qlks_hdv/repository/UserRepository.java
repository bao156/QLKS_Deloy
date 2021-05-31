package qlks_hdv.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import qlks_hdv.entity.User;

public interface UserRepository extends JpaRepository<User, String>,
    JpaSpecificationExecutor<User> {

  Boolean existsByUsername(String username);

  Optional<User> findByUsername(String username);

}
