package com.example.emploinet.controller;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.emploinet.dto.ForgotPasswordRequestDTO;
import com.example.emploinet.dto.ResetPasswordRequestDTO;
import com.example.emploinet.exception.ResourceNotFoundException;
import com.example.emploinet.model.Entreprise;
import com.example.emploinet.model.ResponsableRH;
import com.example.emploinet.model.Role;
import com.example.emploinet.repository.AdministrateurRepository;
import com.example.emploinet.repository.EntrepriseRepository;
import com.example.emploinet.repository.ResponsableRHRepository;
import com.example.emploinet.repository.RoleRepository;
import com.example.emploinet.security.CustomUserDetailsService;
import com.example.emploinet.service.PasswordResetService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private EntrepriseRepository entrepriseRepository;

  @Autowired
  private ResponsableRHRepository responsableRHRepository;

  @Autowired
  private AdministrateurRepository administrateurRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CustomUserDetailsService userDetailsService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordResetService passwordResetService;


    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequestDTO request) {
        // try {
        //   passwordResetService.sendPasswordResetMail(request.getEmail());
        //   return ResponseEntity.status(HttpStatus.OK).body("L'email de réinitialisation du mot de passe a été envoyé");
        // } catch (Exception e) {
        //   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue lors de l'envoi de l'email de réinitialisation du mot de passe");
        // }
        passwordResetService.sendPasswordResetMail(request.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body("L'email de réinitialisation du mot de passe a été envoyé");
    }
    
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequestDTO request) {
        try {
            passwordResetService.resetPassword(request.getLinkIdentifier(), request.getNewPassword());

          

            return ResponseEntity.status(HttpStatus.OK).body("Mot de passe mis à jour avec succes.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour du mot de passe.");
        }
    }

    @PostMapping("/register/entreprise")
    public ResponseEntity<?> registerEntreprise(@RequestBody Entreprise entreprise) {
        // Encode the password
        entreprise.setPassword(passwordEncoder.encode(entreprise.getPassword()));

        // Assign the role to the entreprise
        Role role = roleRepository.findByName("ROLE_ENTERPRISE")
                .orElseGet(() -> roleRepository.save(new Role(null, "ROLE_ENTERPRISE")));
        entreprise.setRoles(Collections.singleton(role));

        // Save the entreprise to the repository
        Entreprise savedEntreprise = entrepriseRepository.save(entreprise);

        // Return a response entity with the saved entreprise
        return new ResponseEntity<>(savedEntreprise, HttpStatus.CREATED);
    }

    @PostMapping("/register/responsableRH/{entrepriseId}")
  public ResponseEntity<ResponsableRH> registerResponsableRH(@RequestBody ResponsableRH responsableRH,
      @PathVariable String entrepriseId) {

    // Check if the entreprise exists

    Entreprise entreprise = entrepriseRepository.findById(entrepriseId)
        .orElseThrow(() -> new ResourceNotFoundException("Entreprise not found with id: " + entrepriseId));

    responsableRH.setPassword(passwordEncoder.encode(responsableRH.getPassword()));

    Role role = roleRepository.findByName("ROLE_RESPONSABLERH")
        .orElseGet(() -> roleRepository.save(new Role(null, "ROLE_RESPONSABLERH")));

    Set<Role> roles = new HashSet<>();
    roles.add(role);
    responsableRH.setRoles(roles);

    // Set the entrepriseId
    responsableRH.setEntrepriseId(entreprise.getId());

    ResponsableRH savedResponsableRH = responsableRHRepository.save(responsableRH);
    return new ResponseEntity<>(savedResponsableRH, HttpStatus.CREATED);
      }

      //Working code

      // @PostMapping("/login/{id}")
    // public ResponseEntity<?> login(@PathVariable String id) {
    //   Entreprise entreprise = entrepriseRepository.findById(id).orElse(null);
    //   if (entreprise != null) {
    //     return new ResponseEntity<>(entreprise, HttpStatus.OK);
    //   }

    //   ResponsableRH responsableRH = responsableRHRepository.findById(id).orElse(null);
    //     if (responsableRH != null) {
    //         return new ResponseEntity<>(responsableRH, HttpStatus.OK);
    //     }

    //     Administrateur administrateur = administrateurRepository.findById(id).orElse(null);
    //     if (administrateur != null) {
    //         return new ResponseEntity<>(administrateur, HttpStatus.OK);
    //     }

    //   return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    // }
}
