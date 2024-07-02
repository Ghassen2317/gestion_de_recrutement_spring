package com.example.emploinet.model;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "administrateurs")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Administrateur extends User {

  public Administrateur(String id, String firstName, String lastName, String email, String password,
      Set<Role> roles) {
    super(id, firstName, lastName, password, email, roles);
  }
}
