package com.example.emploinet.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "responsablesRH")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)

public class ResponsableRH extends User {
  private String matricule;
  @DBRef 
  private Entreprise entreprise ;
  public ResponsableRH(String id, String firstName, String lastName, String email, String matricule, Entreprise entreprise) {
    super(id, firstName, lastName, email);
    this.matricule = matricule;
    this.entreprise = entreprise;
  }
}
