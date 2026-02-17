package com.spring.gateway.mappers.Authentificate;

import com.spring.gateway.dtos.Authentificate.AuthRespDto;
import com.spring.gateway.entities.Authentificate.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public AuthRespDto toAuthResponse(User user, String token) {
    return new AuthRespDto(user.getId(), user.getRole(), token);
  }
}
