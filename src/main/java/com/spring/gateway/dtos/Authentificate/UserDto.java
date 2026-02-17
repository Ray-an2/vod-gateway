package com.spring.gateway.dtos.Authentificate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {
  @NotNull
  private String id;

  @NotBlank(message = "Le pseudo est obligatoire")
  private String pseudo;

  @NotBlank(message = "Le role est obligatoire")
  private String role;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPseudo() {
    return pseudo;
  }

  public void setPseudo(String pseudo) {
    this.pseudo = pseudo;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}