package com.example.emploinet.service;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.emploinet.exception.ResourceNotFoundException;
import com.example.emploinet.model.Entreprise;
import com.example.emploinet.model.PasswordResetRequest;
import com.example.emploinet.model.User;
import com.example.emploinet.repository.EntrepriseRepository;
import com.example.emploinet.repository.PasswordResetRequestRepository;
import com.example.emploinet.repository.UserRepository;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
  

@Service
public class PasswordResetService {

  private static final Logger logger = LoggerFactory.getLogger(PasswordResetService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private EntrepriseService entrepriseService;

    @Autowired
    private PasswordResetRequestRepository resetRequestRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void sendPasswordResetMail(String email) {
      try {
        User user = entrepriseRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("No user found with email: " + email));
        PasswordResetRequest resetRequest = new PasswordResetRequest();

        resetRequest.setLinkIdentifier(UUID.randomUUID().toString());
        resetRequest.setUser(user);
        resetRequest.setExpiryDate(LocalDateTime.now().plusMinutes(10));
        resetRequestRepository.save(resetRequest);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Reinitialisation du mot de passe");
        mailMessage.setText("Cliquez sur ce lien pour reinitialiser votre mot de passe (Ce lien s'expire dans 10 minutes) : " + resetRequest.getLinkIdentifier());
        mailSender.send(mailMessage);
      } catch (MailException e) {
        logger.error("Error sending password reset email for email :", email, e);
        throw new RuntimeException("Failed to send password reset email", e);
      } 
    }

  @Transactional
  public void resetPassword(String linkIdentifier, String newPassword) {
    PasswordResetRequest resetRequest = resetRequestRepository.findByLinkIdentifier(linkIdentifier)
        .orElseThrow(() -> new IllegalArgumentException("Lien invalide"));

    if (resetRequest.getExpiryDate().isBefore(LocalDateTime.now())) {
      throw new IllegalArgumentException("Lien a été expiré");
    }

    entrepriseService.updateForgotPassword(resetRequest.getUser().getEmail(), newPassword);

        resetRequestRepository.delete(resetRequest);
  }
}
