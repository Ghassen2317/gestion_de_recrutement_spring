package com.example.emploinet.model;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "entreprises")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Entreprise extends User {
  private String matriculeFiscale;
  private String nomEntreprise;
  private String siteWeb;
  private String motDePasse;

  public Entreprise(String id, String firstName, String lastName, String email, String matriculeFiscale, String siteWeb,
      String motDePasse, String nomEntreprise, Set<Role> roles) {
    super(id, firstName, lastName, email, roles);
    this.matriculeFiscale = matriculeFiscale;
    this.siteWeb = siteWeb;
    this.motDePasse = motDePasse;
    this.nomEntreprise = nomEntreprise;
  }

}
