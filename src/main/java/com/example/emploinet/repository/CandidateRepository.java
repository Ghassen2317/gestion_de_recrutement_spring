package com.example.emploinet.repository;

//import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

//import com.example.emploinet.enums.TypeContrat;
import com.example.emploinet.model.Candidate;
import com.example.emploinet.model.OffreEmploi;

  public interface CandidateRepository extends MongoRepository<Candidate, String> {
// List<OffreEmploi> findByNomEntreprise(String nomEntreprise);

//   List<OffreEmploi> findByTypeContrat(String typeContrat);

 //List<OffreEmploi> findByDateExpiration(Date dateExpiration);

//    List<OffreEmploi> findByRegion(String region);

//   List<OffreEmploi> findByNbExperienceOffre(Integer nbExperienceOffre);
}
