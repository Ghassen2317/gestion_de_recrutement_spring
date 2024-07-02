package com.example.emploinet.security;

public interface Auth {
  void seConnecter(String email, String motDePasse);
  void seDeconnecter();
}
