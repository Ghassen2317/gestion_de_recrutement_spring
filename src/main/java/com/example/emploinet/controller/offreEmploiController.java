package com.example.emploinet.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.emploinet.enums.TypeContrat;
import com.example.emploinet.model.OffreEmploi;
import com.example.emploinet.service.OffreEmploiService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/offresEmploi")

public class offreEmploiController {
  @Autowired
  private OffreEmploiService offreEmploiService;

  // localhost:8080/api/offresEmploi?nomOffreEmploi=java
 
  @GetMapping("/{nomEntreprise}")
  public List<OffreEmploi> getNomEntreprise(@PathVariable String nomEntreprise) {
    return offreEmploiService.getNomEntreprise(nomEntreprise);
  }

  @PostMapping("/createOffreEmploi")
    public ResponseEntity<OffreEmploi> createOffreEmploi(@RequestBody OffreEmploi offreEmploi) {
        OffreEmploi newOffreEmploi = offreEmploiService.createOffreEmploi(offreEmploi);
        return ResponseEntity.ok(newOffreEmploi);
    }
  
}
