package com.spring.gateway.repositories.Authentificate;

import com.spring.gateway.entities.Authentificate.User;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  private static final long TOKEN_TTL_SECONDS = 3600;

  private final Map<String, User> usersByPseudo = new ConcurrentHashMap<>();
  private final Map<String, Instant> tokenExpirations = new ConcurrentHashMap<>();

  public UserRepository() {
    usersByPseudo.put("bosswasa", new User("1", "bosswasa", "password", "ADMIN"));
    usersByPseudo.put("alice", new User("2", "alice", "alice123", "USER"));
  }

  public Optional<User> findByPseudo(String pseudo) {
    return Optional.ofNullable(usersByPseudo.get(pseudo));
  }

  public void saveToken(String token) {
    tokenExpirations.put(token, Instant.now().plusSeconds(TOKEN_TTL_SECONDS));
  }

  public boolean isTokenValid(String token) {
    Instant expiration = tokenExpirations.get(token);
    if (expiration == null) {
      return false;
    }
    if (Instant.now().isAfter(expiration)) {
      tokenExpirations.remove(token);
      return false;
    }
    return true;
  }
}
