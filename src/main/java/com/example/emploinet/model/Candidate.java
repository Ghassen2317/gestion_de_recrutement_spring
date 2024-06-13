package com.example.emploinet.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "candidates")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Candidate extends User {
  private Integer age;
  private String adresse;
  private Integer nbExperience;
  private String diplome;

  public Candidate(String id, String firstName, String lastName, String adresse, int age, int nbExperience, String diplome,
      String email) {
    super(id, firstName, lastName, email);
    this.adresse = adresse;
    this.age = age;
    this.nbExperience = nbExperience;
    this.diplome = diplome;
  }
}
