package com.spring.gateway.repositories.Authentificate;

import com.spring.gateway.entities.Authentificate.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findByPseudo(String pseudo);
}
