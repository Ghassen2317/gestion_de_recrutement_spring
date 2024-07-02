package com.example.emploinet.service;

import com.example.emploinet.model.Candidature;
import com.example.emploinet.repository.CandidatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.emploinet.enums.Statut;
import com.example.emploinet.exception.ResourceNotFoundException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CandidatureService {

    @Autowired
    private CandidatureRepository candidatureRepository;

    @Autowired
    private JavaMailSender mailSender;

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

    public void sendAcceptationMail(String email) {
        try {
            Candidature candidtature = candidatureRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("Candidature not found"));

            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(candidtature.getEmail());
            mail.setSubject("Candidature acceptée");
            mail.setText("Bonjour " + candidtature.getFirstName() + " " + candidtature.getLastName()
                    + ",\nVotre candidature a été acceptée");
            mailSender.send(mail);
        } catch (MailException e) {
            throw new RuntimeException("Failed to send mail", e);
        }
    }

    public void sendRejectionMail(String email) {
        try {
            Candidature candidtature = candidatureRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("Candidature not found"));

            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(candidtature.getEmail());
            mail.setSubject("Candidature refusée");
            mail.setText("Bonjour " + candidtature.getFirstName() + " " + candidtature.getLastName()
                    + ",\nVotre candidature a été réfusée");
            mailSender.send(mail);
        } catch (MailException e) {
            throw new RuntimeException("Failed to send mail", e);
        }
    }

    public Candidature accepterCandidature(String id) {
        Candidature candidature = candidatureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidature not found"));

        candidature.setStatut(Statut.ACCEPTEE);
        sendAcceptationMail(candidature.getEmail());
        return candidatureRepository.save(candidature);
    }

    public Candidature refuserCandidature(String id) {
        Candidature candidature = candidatureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidature not found"));

        candidature.setStatut(Statut.REFUSEE);
        sendRejectionMail(candidature.getEmail());
        return candidatureRepository.save(candidature);
    }

    public void deleteCandidature(String id) {
        if (!candidatureRepository.existsById(id)) {
            throw new ResourceNotFoundException("Candidature not found");
        }
        candidatureRepository.deleteById(id);
    }

    //Deletes all candidatures
    public void deleteAllCandidatures() {
        candidatureRepository.deleteAll();
    }

    public Candidature uploadCurriculumVitae(String id, MultipartFile file) throws IOException {
        Candidature candidature = candidatureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidature not found"));

        candidature.setCurriculumVitae(file.getBytes());
        return candidatureRepository.save(candidature);
    }

    public Candidature uploadLettreMotivation(String id, MultipartFile file) throws IOException {
        Candidature candidature = candidatureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidature not found"));

        candidature.setLettreMotivation(file.getBytes());
        return candidatureRepository.save(candidature);
    }

    public byte[] getCV(String id) throws FileNotFoundException {
        Candidature candidature = candidatureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidature not found"));
        byte[] fileContent = candidature.getCurriculumVitae();
        if (fileContent == null || fileContent.length == 0) {
            throw new FileNotFoundException("File not found for candidature with id: " + id);
        }
        return fileContent;
    }

    public byte[] getLettreMotivation(String id) throws FileNotFoundException {
        Candidature candidature = candidatureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidature not found"));
        byte[] fileContent = candidature.getLettreMotivation();
        if (fileContent == null || fileContent.length == 0) {
            throw new FileNotFoundException("File not found for candidature with id: " + id);
        }
        return fileContent;
    }
}
