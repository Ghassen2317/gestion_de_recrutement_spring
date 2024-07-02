package com.example.emploinet.controller;

import com.example.emploinet.exception.FileTooLargeException;
import com.example.emploinet.exception.FileTypeNotAllowedException;
import com.example.emploinet.model.Candidature;
import com.example.emploinet.service.CandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidatures")
public class CandidatureController {

    final float MAX_FILE_SIZE = 5 * 1024 * 1024;

    @Autowired
    private CandidatureService candidatureService;

    // Méthode pour créer une nouvelle candidature
    @PostMapping("/create")
    public ResponseEntity<Candidature> createCandidature(@RequestBody Candidature candidature) {
        Candidature newCandidature = candidatureService.createCandidature(candidature);
        return new ResponseEntity<>(newCandidature, HttpStatus.CREATED);
    }

    // methode 1
    // Méthode pour obtenir toutes les candidatures
    @GetMapping("/all")
    public ResponseEntity<List<Candidature>> getAllCandidatures() {
        List<Candidature> candidatures = candidatureService.getAllCandidatures();
        return new ResponseEntity<>(candidatures, HttpStatus.OK);
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

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllCandidatures() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PostMapping("/{id}/upload/cv")
    public ResponseEntity<Candidature> uploadCurriculumVitae(@PathVariable String id,
            @RequestParam("file") MultipartFile file) throws IOException {

        try {
            if (!file.getContentType().equals("application/pdf")) {
                throw new FileTypeNotAllowedException("File type " + file.getContentType() + " is not allowed");
            }

            // If it exceeds 5MB in this case
            if (file.getSize() > MAX_FILE_SIZE) {
                throw new FileTooLargeException("File size exceeds the maximum size allowed");
            }

            Candidature updatedCandidature = candidatureService.uploadCurriculumVitae(id, file);
            return new ResponseEntity<>(updatedCandidature, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/upload/lettre")
    public ResponseEntity<?> uploadLettreMotivation(@PathVariable String id,
            @RequestParam("file") MultipartFile file) throws IOException {

        try {
            if (!file.getContentType().equals("application/pdf")) {
                throw new FileTypeNotAllowedException("File type " + file.getContentType() + " is not allowed");

            }

            if (file.getSize() > MAX_FILE_SIZE) {
                throw new FileTooLargeException("File size exceeds the maximum size allowed");
            }

            Candidature updatedCandidature = candidatureService.uploadLettreMotivation(id, file);
            return new ResponseEntity<>(updatedCandidature, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/download/cv")
    public ResponseEntity<byte[]> downloadCV(@PathVariable String id) throws FileNotFoundException {
        byte[] cv = candidatureService.getCV(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"cv.pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(cv);
    }

    @GetMapping("/{id}/download/lettreMotivation")
    public ResponseEntity<byte[]> downloadLettreMotivation(@PathVariable String id) throws FileNotFoundException {
        byte[] lettreMotivation = candidatureService.getLettreMotivation(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"lettre_motivation.pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(lettreMotivation);
    }
}
