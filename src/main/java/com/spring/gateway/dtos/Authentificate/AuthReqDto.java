package com.spring.gateway.dtos.Authentificate;

public class AuthReqDto {
  private String pseudo;
  private String mdp;

  public String getPseudo() {
    return pseudo;
  }

  public void setPseudo(String pseudo) {
    this.pseudo = pseudo;
  }

  public String getMdp() {
    return mdp;
  }

  public void setMdp(String mdp) {
    this.mdp = mdp;
  }
}
