package com.example.emploinet.model;

import java.util.Set;

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
  private String entrepriseId;

  public ResponsableRH(String id, String firstName, String lastName, String email, String matricule,
      String entrepriseId, Set<Role> roles) {
    super(id, firstName, lastName, email, roles);
    this.matricule = matricule;
    this.entrepriseId = entrepriseId;
  }
}
