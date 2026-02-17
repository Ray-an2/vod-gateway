package com.spring.gateway.mappers.Authentificate;

import com.spring.gateway.dtos.Authentificate.AuthRespDto;
import com.spring.gateway.dtos.Authentificate.UserDto;
import com.spring.gateway.entities.Authentificate.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public AuthRespDto toAuthResponse(User user, String token) {
    return new AuthRespDto(user.getId(), user.getRole(), token);
  }

  public UserDto toDto(User user) {
    if (user == null) {
      return null;
    }
    UserDto dto = new UserDto();
    dto.setId(user.getId());
    dto.setPseudo(user.getPseudo());
    dto.setRole(user.getRole());
    return dto;
  }

  public User toEntity(UserDto dto) {
    return toEntity(dto, null);
  }

  public User toEntity(UserDto dto, String password) {
    if (dto == null) {
      return null;
    }
    User user = new User();
    user.setId(dto.getId());
    user.setPseudo(dto.getPseudo());
    user.setPassword(password);
    user.setRole(dto.getRole());
    return user;
  }
}
