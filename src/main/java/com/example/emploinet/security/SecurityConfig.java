package com.example.emploinet.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {


  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*"); // allow all origins
        config.addAllowedHeader("*"); // allow all headers
        config.addAllowedMethod("GET"); // allow specific HTTP methods
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
      return new CorsFilter(source);
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.cors(Customizer.withDefaults())
        .authorizeHttpRequests(
            auth -> auth.requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/candidatures/**").permitAll()
                .requestMatchers("/api/offresEmploi/byNomEntreprise/**").permitAll()
                .requestMatchers("/api/offresEmploi/byTypeContrat/**").permitAll()
                .requestMatchers("/api/offresEmploi/byNomOffreEmploi/**").permitAll()
                .requestMatchers("/api/offresEmploi/byRegion/**").permitAll()
                .requestMatchers("/api/offresEmploi/byNbExperienceOffre/**").permitAll()

                .requestMatchers("/api/offresEmploi/create").hasAnyRole("ENTREPRISE", "RESPONSABLERH")
                .requestMatchers("/api/offresEmploi/byId/**").hasAnyRole("ENTREPRISE", "RESPONSABLERH")
                .requestMatchers("/api/offresEmploi/updateDateExpiration/**").hasAnyRole("ENTREPRISE", "RESPONSABLERH")
                .requestMatchers("/api/offresEmploi/delete/**").hasAnyRole("ENTREPRISE", "RESPONSABLERH")

                .requestMatchers("api/responsablesRH/**").hasRole("ENTERPRISE")
                .anyRequest().authenticated()) // raja3ha authenticated bch tmchi lform login mrigla sinn kani
                                               // .permitAll() raw les routes mahloulin
        .httpBasic(Customizer.withDefaults())
        .formLogin(form -> form.defaultSuccessUrl("/home", true).successHandler(successHandler()))
        // .httpBasic(httpBasic -> {
        // })
        // .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return httpSecurity.build();
  }

  @Bean
  public SavedRequestAwareAuthenticationSuccessHandler successHandler() {
    SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
    successHandler.setDefaultTargetUrl("/home"); // specify the default target URL
    return successHandler;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
