package com.example.emploinet.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.emploinet.enums.TypeContrat;
import com.example.emploinet.model.OffreEmploi;
import com.example.emploinet.repository.OffreEmploiRepository;

@Service
public class OffreEmploiService {
  @Autowired
  private OffreEmploiRepository offreEmploiRepository;

  public List<OffreEmploi> getNomEntreprise(String nomEntreprise) {
    return offreEmploiRepository.findByNomEntreprise(nomEntreprise);
  }

  public List<OffreEmploi> getNomOffreEmploi(String nomOffreEmploi) {
    return offreEmploiRepository.findByNomOffreEmploi(nomOffreEmploi);
  }

  public List<OffreEmploi> getTypeContrat(TypeContrat typeContrat) {
    return offreEmploiRepository.findByTypeContrat(typeContrat);
  }

  public List<OffreEmploi> getDateExpiration(Date dateExpiration) {
    return offreEmploiRepository.findByDateExpiration(dateExpiration);
  }

  public List<OffreEmploi> getRegion(String region) {
    return offreEmploiRepository.findByRegion(region);
  }

  public List<OffreEmploi> getNbExperienceOffre(Integer nbExperienceOffre) {
    return offreEmploiRepository.findByNbExperienceOffre(nbExperienceOffre);
  }
  public OffreEmploi createOffreEmploi(OffreEmploi offreEmploi) {
    return offreEmploiRepository.save(offreEmploi);
  }

  public void deleteOffreEmploi(OffreEmploi offreEmploi) {
    offreEmploiRepository.delete(offreEmploi);
  }
}
