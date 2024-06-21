package com.example.emploinet.controller;

import com.example.emploinet.model.Candidature;
import com.example.emploinet.service.CandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidatures")
public class CandidatureController {

    @Autowired
    private CandidatureService candidatureService;

    // Méthode pour créer une nouvelle candidature
    @PostMapping
    public ResponseEntity<Candidature> createCandidature(@RequestBody Candidature candidature) {
        Candidature newCandidature = candidatureService.createCandidature(candidature);
        return new ResponseEntity<>(newCandidature, HttpStatus.CREATED);
    }

        // methode 1
    // Méthode pour obtenir toutes les candidatures
    @GetMapping("/all")
    public List<Candidature> getAllCandidatures() {
        return candidatureService.getAllCandidatures();
    }

    // methode 2
    @GetMapping("/offreEmploi/{nomOffreEmploi}")
    public ResponseEntity<List<Candidature>> getCandidatureByNomOffreEmploi(@PathVariable String nomOffreEmploi) {
        List<Candidature> candidatures = candidatureService.getCandidatureByNomOffreEmploi(nomOffreEmploi);
        return new ResponseEntity<>(candidatures, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Candidature>> getCandidatureById(@PathVariable String id) {
        Optional<Candidature> candidatures = candidatureService.getCandidatureById(id);
        return new ResponseEntity<>(candidatures, HttpStatus.OK);
    }

    @PutMapping("/{id}/accepter")
    public ResponseEntity<Candidature> accepterCandidature(@PathVariable String id) {
        Candidature updatedCandidature = candidatureService.accepterCandidature(id);
        return new ResponseEntity<>(updatedCandidature, HttpStatus.OK);
    }
    @PutMapping("/{id}/refuser")
    public ResponseEntity<Candidature> refuserCandidature(@PathVariable String id) {
        Candidature updatedCandidature = candidatureService.refuserCandidature(id);
        return new ResponseEntity<>(updatedCandidature, HttpStatus.OK);
    }

    // Méthode pour supprimer une candidature par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidature(@PathVariable String id) {
        candidatureService.deleteCandidature(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
