package qlks_hdv.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

  Boolean existsByUsername(String username);

  Optional<User> findByUsername(String username);


}
