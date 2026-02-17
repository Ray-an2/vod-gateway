package com.spring.gateway.dtos.Authentificate;

public class TokenVerifDto {
  private boolean valid;

  public TokenVerifDto() {
  }

  public TokenVerifDto(boolean valid) {
    this.valid = valid;
  }

  public boolean isValid() {
    return valid;
  }

  public void setValid(boolean valid) {
    this.valid = valid;
  }
}
