package com.example.emploinet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.emploinet.model.Entreprise;
import com.example.emploinet.model.ResponsableRH;
import com.example.emploinet.repository.EntrepriseRepository;
import com.example.emploinet.repository.ResponsableRHRepository;

@Service
public class ResponsableRHService {
  @Autowired
  private ResponsableRHRepository responsableRHRepository;

  @Autowired
  private EntrepriseRepository entrepriseRepository;

  public ResponsableRH createResponsableRH(ResponsableRH responsableRH, String entrepriseId) {
    Entreprise entreprise = entrepriseRepository.findById(entrepriseId)
        .orElseThrow(() -> new RuntimeException("Entreprise not found"));
    responsableRH.setEntrepriseId(entreprise.getId());
    return responsableRHRepository.save(responsableRH);
  }

  public List<ResponsableRH> getRhByEntrepriseId(String entrepriseId) {
    return responsableRHRepository.findByEntrepriseId(entrepriseId);
  }

  public Optional<ResponsableRH> getRhByMatricule(String matricule) {
    return responsableRHRepository.findByMatricule(matricule);
  }

  public void deleteResponsableRH(String id) {
    responsableRHRepository.deleteById(id);
  }

}
