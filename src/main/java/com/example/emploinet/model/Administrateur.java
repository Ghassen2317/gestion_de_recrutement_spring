package com.example.emploinet.model;

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

  public Administrateur(String id, String firstName, String lastName, String email, String motDePasse) {
    super(id, firstName, lastName, email);
    this.motDePasse = motDePasse;
  }
}
