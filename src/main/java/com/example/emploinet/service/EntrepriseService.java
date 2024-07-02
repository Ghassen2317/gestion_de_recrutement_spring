package com.example.emploinet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.emploinet.exception.ResourceNotFoundException;
import com.example.emploinet.model.Entreprise;
import com.example.emploinet.repository.EntrepriseRepository;

@Service
public class EntrepriseService {
  @Autowired
  private EntrepriseRepository entrepriseRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public List<Entreprise> getAllEntreprise() {
    return entrepriseRepository.findAll();
  }

  public Entreprise getEntrepriseByNom(String nomEntreprise) {
    return entrepriseRepository.findByNomEntreprise(nomEntreprise);
  }

  public Optional<Entreprise> getEntrepriseById(String id) {
    return entrepriseRepository.findById(id);
  }

  public Optional<Entreprise> getEntrepriseByEmail(String email) {
    return entrepriseRepository.findByEmail(email);
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
      throw new ResourceNotFoundException("Entreprise not found");
  }

  public Entreprise updateMotDePasse(String id, Entreprise updatedEntreprise) {
    Optional<Entreprise> existingEntreprise = getEntrepriseById(id);
    if (existingEntreprise.isPresent()) {
      updatedEntreprise.setPassword(id);
      return entrepriseRepository.save(updatedEntreprise);
    } else
      throw new ResourceNotFoundException("Entreprise not found");
  }

  public Entreprise updateForgotPassword(String email, String password) {
    Optional<Entreprise> existingEntreprise = getEntrepriseByEmail(email);
    if (existingEntreprise.isPresent()) {
      Entreprise entreprise = existingEntreprise.get();

      if (password != null) {
        
        String encodedPassword = passwordEncoder.encode(password);
        entreprise.setPassword(encodedPassword);
      }
      else {
        throw new IllegalArgumentException("New password cannot be null");
      }

      return entrepriseRepository.save(entreprise);
    } else
      throw new ResourceNotFoundException("Entreprise not found");
  }

  public Entreprise updateSiteWeb(String id, Entreprise updatedEntreprise) {
    Optional<Entreprise> existingEntreprise = getEntrepriseById(id);
    if (existingEntreprise.isPresent()) {
      updatedEntreprise.setSiteWeb(id);
      return entrepriseRepository.save(updatedEntreprise);
    } else
      throw new ResourceNotFoundException("Entreprise not found");
  }

  public Entreprise updateMatriculeFiscale(String id, Entreprise updatedEntreprise) {
    Optional<Entreprise> existingEntreprise = getEntrepriseById(id);
    if (existingEntreprise.isPresent()) {
      updatedEntreprise.setMatriculeFiscale(id);
      return entrepriseRepository.save(updatedEntreprise);
    } else
      throw new ResourceNotFoundException("Entreprise not found");
  }

  public void deleteEntreprise(String id) {
    if (!entrepriseRepository.existsById(id)) {
      throw new ResourceNotFoundException("Entreprise not found");
    }
    entrepriseRepository.deleteById(id);
  }
}
