package com.example.emploinet.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "candidates")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Candidate {

  @Id
  private String id;
  private String firstName;
  private String lastName;
  private Integer age;
  private String adresse;
  private Integer nbExperience;
  private String diplome;

  public Candidate(String id, String firstName, String lastName, String adresse, int age, int nbExperience,
      String diplome,
      String email) {
    this.adresse = adresse;
    this.age = age;
    this.nbExperience = nbExperience;
    this.diplome = diplome;
  }
}
