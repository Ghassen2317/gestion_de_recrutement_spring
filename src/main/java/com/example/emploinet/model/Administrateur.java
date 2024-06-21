package com.example.emploinet.model;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "administrateurs")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Administrateur extends User {
  private String motDePasse;

  public Administrateur(String id, String firstName, String lastName, String email, String motDePasse,
      Set<Role> roles) {
    super(id, firstName, lastName, email, roles);
    this.motDePasse = motDePasse;
  }
}
