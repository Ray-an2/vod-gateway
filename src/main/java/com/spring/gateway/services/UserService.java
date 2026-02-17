package com.spring.gateway.services;

import com.spring.gateway.dtos.Authentificate.AuthReqDto;
import com.spring.gateway.dtos.Authentificate.AuthRespDto;

public interface UserService {
  AuthRespDto login(AuthReqDto request);
  boolean verifyToken(String authorizationHeader);
}
