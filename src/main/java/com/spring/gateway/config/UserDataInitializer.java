package com.spring.gateway.config;

import com.spring.gateway.entities.Authentificate.User;
import com.spring.gateway.repositories.Authentificate.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDataInitializer implements CommandLineRunner {

  private final UserRepository userRepository;

  public UserDataInitializer(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void run(String... args) {
    if (userRepository.findByPseudo("bosswasa").isEmpty()) {
      userRepository.save(new User("1", "bosswasa", "password", "ADMIN"));
    }
    if (userRepository.findByPseudo("alice").isEmpty()) {
      userRepository.save(new User("2", "alice", "alice123", "USER"));
    }
  }
}
