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
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)

public class ResponsableRH extends User {

  private String entrepriseId;

  public ResponsableRH(String id, String firstName, String lastName, String email,
      String entrepriseId, String password, Set<Role> roles) {
    super(id, firstName, lastName, email, password, roles);
    this.entrepriseId = entrepriseId;
  }
}
