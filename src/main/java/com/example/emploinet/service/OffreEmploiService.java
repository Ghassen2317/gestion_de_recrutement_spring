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

  public OffreEmploi createOffreEmploi(OffreEmploi offreEmploi) {
    return offreEmploiRepository.save(offreEmploi);
}

}
