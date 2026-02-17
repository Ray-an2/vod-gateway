package com.spring.gateway.controllers.Authentificate;

import com.spring.gateway.dtos.Authentificate.AuthReqDto;
import com.spring.gateway.dtos.Authentificate.AuthRespDto;
import com.spring.gateway.dtos.Authentificate.TokenVerifDto;
import com.spring.gateway.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  public AuthRespDto login(@RequestBody AuthReqDto request) {
    return userService.login(request);
  }

  @GetMapping("/verify-token")
  public TokenVerifDto verifyToken(@RequestHeader("Authorization") String authorization) {
    boolean valid = userService.verifyToken(authorization);
    if (!valid) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token invalide ou expiré");
    }
    return new TokenVerifDto(true);
  }
}
