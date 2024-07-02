// package com.example.emploinet.security;

// import java.util.Collections;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import com.example.emploinet.model.Entreprise;
// import com.example.emploinet.model.ResponsableRH;
// import com.example.emploinet.repository.EntrepriseRepository;
// import com.example.emploinet.repository.ResponsableRHRepository;

// @Service
// public class CustomUserDetailsService implements UserDetailsService {

//   @Autowired
//   private EntrepriseRepository entrepriseRepository;

//   @Autowired
//   private ResponsableRHRepository responsableRHRepository;

//   @Override
//   public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
//     // Try to find the user as an Entreprise
//     Entreprise entreprise = entrepriseRepository.findByEmail(identifier).orElse(null);
//     if (entreprise != null) {
//         return new User(entreprise.getEmail(), entreprise.getPassword(),
//             entreprise.getRoles().stream()
//                 .map(role -> new SimpleGrantedAuthority(role.getName()))
//                 .collect(Collectors.toList()));
//     }

//     // Try to find the user as a ResponsableRH
//     ResponsableRH responsableRH = responsableRHRepository.findByEmail(identifier).orElse(null);
//     if (responsableRH != null) {
//         return new User(responsableRH.getEmail(), responsableRH.getPassword(),
//             responsableRH.getRoles().stream()
//                 .map(role -> new SimpleGrantedAuthority(role.getName()))
//                 .collect(Collectors.toList()));
//     }

//     // If the user is not found in both repositories, throw an exception
//     throw new UsernameNotFoundException("User not found with email: " + identifier);
// } }

// @Override
// public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
//   if (identifier.contains("@")) {
//     Entreprise entreprise = entrepriseRepository.findByEmail(identifier)
//         .orElseThrow(() -> new UsernameNotFoundException("Entreprise not found with email: " + identifier));
//     return new User(entreprise.getEmail(), entreprise.getPassword(),
//         entreprise.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_ENTERPRISE"))
//             .collect(Collectors.toList()));
//   } else {
//     ResponsableRH responsableRH = responsableRHRepository.findByEmail(identifier)
//         .orElseThrow(() -> new UsernameNotFoundException("ResponsableRH not found with matricule: " + identifier));

//     return new User(responsableRH.getEmail(), responsableRH.getPassword(),
//         Collections.singletonList(new SimpleGrantedAuthority("ROLE_RESPONSABLERH")));
//   }
// }

package com.example.emploinet.security;

import com.example.emploinet.model.Administrateur;
import com.example.emploinet.model.Entreprise;
import com.example.emploinet.model.ResponsableRH;
import com.example.emploinet.repository.AdministrateurRepository;
import com.example.emploinet.repository.EntrepriseRepository;
import com.example.emploinet.repository.ResponsableRHRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

        private final Logger logger = Logger.getLogger(CustomUserDetailsService.class.getName());

        @Autowired
        private EntrepriseRepository entrepriseRepository;

        @Autowired
        private ResponsableRHRepository responsableRHRepository;

        @Autowired
        private AdministrateurRepository administrateurRepository;

        @Override
        public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
                Entreprise entreprise = entrepriseRepository.findByEmail(identifier)
                                .orElseThrow(() -> new UsernameNotFoundException(
                                                "Entreprise not found with email: " + identifier));
                if (entreprise != null) {
                        logger.info("Loaded Entreprise: " + entreprise.getEmail() + " with roles: " +
                                        entreprise.getRoles().stream()
                                                        .map(role -> role.getName())
                                                        .collect(Collectors.joining(", ")));
                        return new User(entreprise.getEmail(), entreprise.getPassword(),
                                        entreprise.getRoles().stream()
                                                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                                                        .collect(Collectors.toList()));
                }
                ResponsableRH responsableRH = responsableRHRepository.findByEmail(identifier)
                                .orElseThrow(
                                                () -> new UsernameNotFoundException(
                                                                "ResponsableRH not found with email: "
                                                                                + identifier));

                if (responsableRH != null) {

                        return new User(responsableRH.getEmail(), responsableRH.getPassword(),
                                        responsableRH.getRoles().stream()
                                                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                                                        .collect(Collectors.toList()));
                }

                Administrateur administrateur = administrateurRepository.findByEmail(identifier);

                if (administrateur != null) {
                        return new User(administrateur.getEmail(), administrateur.getPassword(),
                                        administrateur.getRoles().stream()
                                                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                                                        .collect(Collectors.toList()));
                }
                throw new UsernameNotFoundException("User not found with email: " + identifier);
        }
}
