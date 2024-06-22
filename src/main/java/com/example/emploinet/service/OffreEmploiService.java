package com.example.emploinet.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.emploinet.enums.TypeContrat;
import com.example.emploinet.exception.ResourceNotFoundException;
import com.example.emploinet.model.OffreEmploi;
import com.example.emploinet.repository.OffreEmploiRepository;



@Service
public class OffreEmploiService {
  @Autowired
  private OffreEmploiRepository offreEmploiRepository;

  public List<OffreEmploi> getAllOffreEmploi() {
    return offreEmploiRepository.findAll();
  }

  public Optional<OffreEmploi> getOffreEmploiById(String id) {
    return offreEmploiRepository.findById(id);
  }

  public List<OffreEmploi> getNomEntreprise(String nomEntreprise) {
    return offreEmploiRepository.findByNomEntreprise(nomEntreprise);
  }

  public OffreEmploi createOffreEmploi(OffreEmploi offreEmploi) {
    return offreEmploiRepository.save(offreEmploi);
  }

  public List<OffreEmploi> getNomOffreEmploi(String nomOffreEmploi) {
    return offreEmploiRepository.findByNomOffreEmploi(nomOffreEmploi);
  }

  public OffreEmploi updateDateExpiration(String id, Date dateExpiration) {
    Optional<OffreEmploi> optionalOffreEmploi = offreEmploiRepository.findById(id);
    if (optionalOffreEmploi.isPresent()) {
      OffreEmploi offreEmploi = optionalOffreEmploi.get();
      offreEmploi.setDateExpiration(dateExpiration);
      return offreEmploiRepository.save(offreEmploi);
    } 
    else {
      throw new ResourceNotFoundException("OffreEmploi not found with id: " + id);
    }
  }

  public void deleteOffreEmploi(String id) {
    offreEmploiRepository.deleteById(id);
  }

  public List<OffreEmploi> getTypeContrat(TypeContrat typeContrat) {
  return offreEmploiRepository.findByTypeContrat(typeContrat);
  }

  // public List<OffreEmploi> getDateExpiration(Date dateExpiration) {
  // return offreEmploiRepository.findByDateExpiration(dateExpiration);
  // }

  public List<OffreEmploi> getRegion(String region) {
  return offreEmploiRepository.findByRegion(region);
  }

  public List<OffreEmploi> getNbExperienceOffre(Integer nbExperienceOffre) {
  return offreEmploiRepository.findByNbExperienceOffre(nbExperienceOffre);
  }

}
