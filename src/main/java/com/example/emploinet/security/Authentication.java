package com.example.emploinet.security;

public interface Authentication {
  void seConnecter(String email, String motDePasse);
  void seDeconnecter();
}
