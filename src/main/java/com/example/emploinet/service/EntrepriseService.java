package com.example.emploinet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.emploinet.model.Entreprise;
import com.example.emploinet.repository.EntrepriseRepository;

@Service
public class EntrepriseService {
  @Autowired
  private EntrepriseRepository entrepriseRepository;

  public List<Entreprise> getAllEntreprise() {
    return entrepriseRepository.findAll();
  }

  public Optional<Entreprise> getEntrepriseById(String id) {
    return entrepriseRepository.findById(id);
  }

  public Entreprise createEntreprise(Entreprise entreprise) {
    return entrepriseRepository.save(entreprise);
  }

  public Entreprise updateNomEntreprise(String id, Entreprise updatedEntreprise) {
    Optional<Entreprise> existingEntreprise = getEntrepriseById(id);
    if (existingEntreprise.isPresent()) {
      updatedEntreprise.setNomEntreprise(id);
      return entrepriseRepository.save(updatedEntreprise);
    } else
      return null;
  }

  public Entreprise updateMotDePasse(String id, Entreprise updatedEntreprise) {
    Optional<Entreprise> existingEntreprise = getEntrepriseById(id);
    if (existingEntreprise.isPresent()) {
      updatedEntreprise.setMotDePasse(id);
      return entrepriseRepository.save(updatedEntreprise);
    } else
      return null;
  }

  public Entreprise updateSiteWeb(String id, Entreprise updatedEntreprise) {
    Optional<Entreprise> existingEntreprise = getEntrepriseById(id);
    if (existingEntreprise.isPresent()) {
      updatedEntreprise.setSiteWeb(id);
      return entrepriseRepository.save(updatedEntreprise);
    } else
      return null;
  }

  public Entreprise updateMatriculeFiscale(String id, Entreprise updatedEntreprise) {
    Optional<Entreprise> existingEntreprise = getEntrepriseById(id);
    if (existingEntreprise.isPresent()) {
      updatedEntreprise.setMatriculeFiscale(id);
      return entrepriseRepository.save(updatedEntreprise);
    } else
      return null;
  }

  

  public void deleteEntreprise(String id) {
    entrepriseRepository.deleteById(id);
  }
}
