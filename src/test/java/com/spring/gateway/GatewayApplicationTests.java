package com.spring.gateway;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.spring.gateway.dtos.Authentificate.AuthReqDto;
import com.spring.gateway.dtos.Authentificate.AuthRespDto;
import com.spring.gateway.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GatewayApplicationTests {

  @Autowired
  private UserService userService;

  @Test
  void contextLoads() {
  }

  @Test
  void loginAndVerifyTokenFlow() {
    AuthReqDto request = new AuthReqDto();
    request.setPseudo("bosswasa");
    request.setMdp("password");

    AuthRespDto response = userService.login(request);
    boolean valid = userService.verifyToken("Bearer " + response.getToken());

    assertTrue(valid);
  }
}
