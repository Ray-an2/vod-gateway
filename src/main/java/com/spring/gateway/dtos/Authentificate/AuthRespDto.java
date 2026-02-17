package com.spring.gateway.dtos.Authentificate;

public class AuthRespDto {
  private String id;
  private String role;
  private String token;

  public AuthRespDto() {
  }

  public AuthRespDto(String id, String role, String token) {
    this.id = id;
    this.role = role;
    this.token = token;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
