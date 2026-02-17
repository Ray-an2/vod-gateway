package com.spring.gateway.entities.Authentificate;

public class User {
  private final String id;
  private final String pseudo;
  private final String password;
  private final String role;

  public User(String id, String pseudo, String password, String role) {
    this.id = id;
    this.pseudo = pseudo;
    this.password = password;
    this.role = role;
  }

  public String getId() {
    return id;
  }

  public String getPseudo() {
    return pseudo;
  }

  public String getPassword() {
    return password;
  }

  public String getRole() {
    return role;
  }
}
