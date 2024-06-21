package com.example.emploinet.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.emploinet.enums.TypeContrat;
import com.example.emploinet.model.OffreEmploi;

public interface OffreEmploiRepository extends MongoRepository<OffreEmploi, String> {
  List<OffreEmploi> findByNomEntreprise(String nomEntreprise);

  List<OffreEmploi> findByNomOffreEmploi(String nomOffreEmploi);

  List<OffreEmploi> findByTypeContrat(TypeContrat typeContrat);

  List<OffreEmploi> findByDateExpiration(Date dateExpiration);

  List<OffreEmploi> findByRegion(String region);

  List<OffreEmploi> findByNbExperienceOffre(Integer nbExperienceOffre);

}
