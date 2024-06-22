package com.example.emploinet.controller;

import com.example.emploinet.enums.TypeContrat;
import com.example.emploinet.model.OffreEmploi;
import com.example.emploinet.service.CandidatService;
import com.example.emploinet.service.OffreEmploiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/offresEmploi")
public class CandidatController {

    @Autowired
    private CandidatService candidatService;
    @Autowired
    private OffreEmploiService offreEmploiService;
 @GetMapping("/All")
  public List<OffreEmploi> getAllOffreEmploi() {
    return offreEmploiService.getAllOffreEmploi();
  }

  @GetMapping("/byid/{id}")
  public Optional<OffreEmploi> getOffreEmploiById(@PathVariable String id) {
    return offreEmploiService.getOffreEmploiById(id);
  }

  @GetMapping("/byEntreprise/{nomEntreprise}")
  public List<OffreEmploi> getNomEntreprise(@PathVariable String nomEntreprise) {
    return offreEmploiService.getNomEntreprise(nomEntreprise);
  }

  @GetMapping("/byContrat/{typeContrat}")
  public List<OffreEmploi> getTypeContrat(@PathVariable TypeContrat typeContrat) {
    return offreEmploiService.getTypeContrat(typeContrat);
  }

  @GetMapping("/byNomOffre/{nomOffreEmploi}")
  public List<OffreEmploi> getNomOffreEmploi(@PathVariable String nomOffreEmploi) {
    return offreEmploiService.getNomOffreEmploi(nomOffreEmploi);
  }

}
