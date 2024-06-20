

// package com.example.emploinet.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.example.emploinet.enums.Statut;
// import com.example.emploinet.model.Candidature;
// import com.example.emploinet.repository.CandidatureRepository;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class CandidatureService {

//     @Autowired
//     private CandidatureRepository candidatureRepository;

//     public Candidature saveCandidature(Candidature candidature) {
//         return candidatureRepository.save(candidature);
//     }

//     public List<Candidature> getAllCandidatures() {
//         return candidatureRepository.findAll();
//     }

//     public Optional<Candidature> getCandidatureById(String id) {
//         return candidatureRepository.findById(id);
//     }

//     public List<Candidature> getCandidaturesByNomOffreEmploi(String nomOffreEmploi) {
//         return candidatureRepository.findByNomOffreEmploi(nomOffreEmploi);
//     }

//     public void deleteCandidatureById(String id) {
//         candidatureRepository.deleteById(id);
//     }

//     public Candidature acceptCandidature(String id) {
//         Optional<Candidature> optionalCandidature = candidatureRepository.findById(id);
//         if (optionalCandidature.isPresent()) {
//             Candidature candidature = optionalCandidature.get();
//             candidature.setStatut(Statut.ACCEPTEE);
//             return candidatureRepository.save(candidature);
//         }
//         return null;
//     }
// }

package com.example.emploinet.service;

import com.example.emploinet.model.Candidature;
import com.example.emploinet.model.OffreEmploi;
import com.example.emploinet.repository.CandidatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.emploinet.enums.Statut;
import com.example.emploinet.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatureService {

    @Autowired
    private CandidatureRepository candidatureRepository;

    public Candidature createCandidature(Candidature candidature) {
        return candidatureRepository.save(candidature);
    }

    public List<Candidature> getAllCandidatures() {
        return candidatureRepository.findAll();
    }

    
    public List<Candidature> getCandidatureByNomOffreEmploi(String nomOffreEmploi) {
        return candidatureRepository.findByNomOffreEmploi(nomOffreEmploi);
    }
    public Optional<Candidature> getCandidatureById(String id) {
        return candidatureRepository.findById(id);
    }
    public Candidature accepterCandidature(String id) {
        Candidature candidature = candidatureRepository.findById(id).orElseThrow(() -> new RuntimeException("Candidature not found"));
        candidature.setStatut(Statut.ACCEPTEE);
        return candidatureRepository.save(candidature);
    }
    public Candidature refuserCandidature(String id) {
        Candidature candidature = candidatureRepository.findById(id).orElseThrow(() -> new RuntimeException("Candidature not found"));
        candidature.setStatut(Statut.REFUSEE);
        return candidatureRepository.save(candidature);
    }

    public void deleteCandidature(String id) {
        candidatureRepository.deleteById(id);
    }
}
