package com.spring.gateway.services.impl.Authentificate;

import com.spring.gateway.dtos.Authentificate.AuthReqDto;
import com.spring.gateway.dtos.Authentificate.AuthRespDto;
import com.spring.gateway.entities.Authentificate.User;
import com.spring.gateway.mappers.Authentificate.UserMapper;
import com.spring.gateway.repositories.Authentificate.UserRepository;
import java.time.Instant;
import java.util.Map;
import com.spring.gateway.services.UserService;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class UserServiceImpl implements UserService {

  private static final long TOKEN_TTL_SECONDS = 3600;

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final Map<String, Instant> tokenExpirations = new ConcurrentHashMap<>();

  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  @Override
  public AuthRespDto login(AuthReqDto request) {
    if (request == null || request.getPseudo() == null || request.getMdp() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le pseudo et le mot de passe sont obligatoires");
    }

    Optional<User> optionalUser = userRepository.findByPseudo(request.getPseudo());
    if (optionalUser.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé");
    }

    User user = optionalUser.get();
    if (!user.getPassword().equals(request.getMdp())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants invalides");
    }

    String token = UUID.randomUUID().toString();
    tokenExpirations.put(token, Instant.now().plusSeconds(TOKEN_TTL_SECONDS));
    return userMapper.toAuthResponse(user, token);
  }

  @Override
  public boolean verifyToken(String authorizationHeader) {
    if (authorizationHeader == null || authorizationHeader.isBlank()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Header Authorization obligatoire");
    }

    String prefix = "Bearer ";
    if (!authorizationHeader.startsWith(prefix)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le header Authorization doit commencer par 'Bearer '");
    }

    String token = authorizationHeader.substring(prefix.length()).trim();
    if (token.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token vide");
    }

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
