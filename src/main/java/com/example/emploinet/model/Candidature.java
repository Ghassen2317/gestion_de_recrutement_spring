package com.example.emploinet.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.emploinet.enums.Diplome;
import com.example.emploinet.enums.Sexe;
import com.example.emploinet.enums.Statut;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "candidatures")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Candidature {

  @Id
  private String id;
  private String firstName;
  private String lastName;
  private String email;
  private Integer age;
  private String adresse;
  private Integer nbExperience;
  private Diplome diplome; 
  private Sexe sexe; 
  private String nomOffreEmploi;
  private Date datePostulation;
  private byte[] curriculumVitae;
  private byte[] lettreMotivation;
  private Statut statut;
  
}
