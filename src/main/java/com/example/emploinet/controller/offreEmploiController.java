package com.example.emploinet.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.emploinet.enums.TypeContrat;
import com.example.emploinet.model.OffreEmploi;
import com.example.emploinet.service.OffreEmploiService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/offresEmploi")

public class offreEmploiController {
  @Autowired
  private OffreEmploiService offreEmploiService;

  // localhost:8080/api/offresEmploi?nomOffreEmploi=java
  @GetMapping("")
  public List<OffreEmploi> getNomOffreEmploi(@RequestParam String nomOffreEmploi) {
    return offreEmploiService.getNomOffreEmploi(nomOffreEmploi);
  }

  @GetMapping("")
  public List<OffreEmploi> getNomEntreprise(@RequestParam String nomEntreprise) {
    return offreEmploiService.getNomEntreprise(nomEntreprise);
  }

  @GetMapping("")
  public List<OffreEmploi> getTypeContrat(@RequestParam TypeContrat typeContrat) {
    return offreEmploiService.getTypeContrat(typeContrat);
  }

  @GetMapping("")
  public List<OffreEmploi> getDateExpiration(@RequestParam Date dateExpiration) {
    return offreEmploiService.getDateExpiration(dateExpiration);
  }

  @GetMapping("")
  public List<OffreEmploi> getRegion(@RequestParam String region) {
    return offreEmploiService.getRegion(region);
  }

  @GetMapping("")
  public List<OffreEmploi> getNbExperienceOffre(@RequestParam Integer nbExperienceOffre) {
    return offreEmploiService.getNbExperienceOffre(nbExperienceOffre);
  }
}
