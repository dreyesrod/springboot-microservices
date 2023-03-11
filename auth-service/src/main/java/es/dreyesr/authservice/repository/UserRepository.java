package es.dreyesr.authservice.repository;

import java.util.Optional;

import es.dreyesr.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

}
