package com.example.emploinet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.emploinet.enums.TypeContrat;
import com.example.emploinet.model.OffreEmploi;
//import com.example.emploinet.repository.CandidateRepository;
import java.util.stream.Collectors;


@Service
public class CandidatService {
  // @Autowired
  // private CandidateRepository candidateRepository;
  @Autowired
  private OffreEmploiService offreEmploiService; // Injection de OffreEmploiService
  public List<OffreEmploi> getTypeContrat(TypeContrat typeContrat) {
  return offreEmploiService.getTypeContrat(typeContrat);
  }
  public List<OffreEmploi> getNomOffreEmploi(String nomOffreEmploi) {
    return offreEmploiService.getNomOffreEmploi(nomOffreEmploi);
  }

  public List<OffreEmploi> getRegion(String region) {
  return offreEmploiService.getRegion(region);
  }

  public List<OffreEmploi> getNbExperienceOffre(Integer nbExperienceOffre) {
  return offreEmploiService.getNbExperienceOffre(nbExperienceOffre);
  }
}
