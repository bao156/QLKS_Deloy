package qlks_hdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
