package com.example.emploinet.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.emploinet.enums.Diplome;
import com.example.emploinet.enums.Sexe;

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
  private String email;
  private Integer age;
  private String adresse;
  private Integer nbExperience;
  private Diplome diplome; // convert into enum
  private Sexe sexe; // convert into enum

  public Candidate(String id, String firstName, String lastName, String adresse, int age, int nbExperience,
      Diplome diplome, Sexe sexe,
      String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.adresse = adresse;
    this.age = age;
    this.nbExperience = nbExperience;
    this.diplome = diplome;
    this.sexe = sexe;
  }
}
