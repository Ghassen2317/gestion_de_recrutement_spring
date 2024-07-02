package com.example.emploinet.controller;

import com.example.emploinet.enums.Sexe;
import com.example.emploinet.enums.TypeContrat;
import com.example.emploinet.model.Candidate;
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
  public ResponseEntity<List<OffreEmploi>> getAllOffreEmploi() {
    List<OffreEmploi> offreEmplois = offreEmploiService.getAllOffreEmploi();
    return new ResponseEntity<>(offreEmplois, HttpStatus.OK);
  }

  @GetMapping("/byid/{id}")
  public ResponseEntity<Optional<OffreEmploi>> getOffreEmploiById(@PathVariable String id) {
    Optional<OffreEmploi> offreEmploi = offreEmploiService.getOffreEmploiById(id);
    return new ResponseEntity<>(offreEmploi, HttpStatus.OK);
  }

  @GetMapping("/byEntreprise/{nomEntreprise}")
  public ResponseEntity<List<OffreEmploi>> getNomEntreprise(@PathVariable String nomEntreprise) {
    List<OffreEmploi> offreEmplois = offreEmploiService.getNomEntreprise(nomEntreprise);
    return new ResponseEntity<>(offreEmplois, HttpStatus.OK);
  }

  @GetMapping("/byContrat/{typeContrat}")
  public ResponseEntity<List<OffreEmploi>> getTypeContrat(@PathVariable TypeContrat typeContrat) {
    List<OffreEmploi> offreEmplois = offreEmploiService.getTypeContrat(typeContrat);
    return new ResponseEntity<>(offreEmplois, HttpStatus.OK);
  }

  @GetMapping("/byNomOffre/{nomOffreEmploi}")
  public ResponseEntity<List<OffreEmploi>> getNomOffreEmploi(@PathVariable String nomOffreEmploi) {
    List<OffreEmploi> offreEmplois = offreEmploiService.getNomOffreEmploi(nomOffreEmploi);
    return new ResponseEntity<>(offreEmplois, HttpStatus.OK);
  }

  @GetMapping("/bySexe/{sexe}")
  public ResponseEntity<Candidate> getSexe(@PathVariable Sexe sexe) {
    Candidate candidate = candidatService.getSexe(sexe);
    return new ResponseEntity<>(candidate, HttpStatus.OK);
  }

}