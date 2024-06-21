package com.example.emploinet.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.emploinet.enums.TypeContrat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "offresEmploi")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OffreEmploi {
  @Id
  private String id;
  private String nomOffreEmploi;
  private String nomEntreprise;
  private String description;
  private String region;
  private Integer nbExperienceOffre;
  private TypeContrat typeContrat;
  private Date datePublication;
  private Date dateExpiration;
}


