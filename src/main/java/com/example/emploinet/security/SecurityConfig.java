package com.example.emploinet.security;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

  // @Bean
  // public CorsFilter corsFilter() {
  // UrlBasedCorsConfigurationSource source = new
  // UrlBasedCorsConfigurationSource();
  // CorsConfiguration config = new CorsConfiguration();
  // config.setAllowCredentials(true);
  // config.addAllowedOrigin(" http://localhost:4200/"); // allow all origins
  // config.addAllowedHeader("*"); // allow all headers
  // config.addAllowedMethod("GET"); // allow specific HTTP methods
  // config.addAllowedMethod("POST");
  // config.addAllowedMethod("PUT");
  // config.addAllowedMethod("DELETE");
  // source.registerCorsConfiguration("/**", config);

  // source.registerCorsConfiguration("/**", config);

  // return new CorsFilter(source);
  // }
  @Autowired
  private CustomAuthenticationSuccessHandler successHandler;

  @Bean
  public UrlBasedCorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.addAllowedOrigin("http://localhost:4200");
    corsConfiguration.addAllowedHeader("*");
    corsConfiguration.addAllowedMethod("*");
    corsConfiguration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfiguration);

    return source;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .authorizeHttpRequests(
            auth -> auth.requestMatchers("/api/auth/**").permitAll() // raja3ha mch permit all lel register/resporh
                                                                     // khatr maynajem ycreati rh kan l'entreprise
                .requestMatchers("/candidatures/**").permitAll()
                .requestMatchers("/api/offresEmploi/byNomEntreprise/**").permitAll()
                .requestMatchers("/api/offresEmploi/byTypeContrat/**").permitAll()
                .requestMatchers("/api/offresEmploi/byNomOffreEmploi/**").permitAll()
                .requestMatchers("/api/offresEmploi/byRegion/**").permitAll()
                .requestMatchers("/api/offresEmploi/byNbExperienceOffre/**").permitAll()
                // .requestMatchers("/dashboard").authenticated()
                .requestMatchers("/api/offresEmploi/create").hasAnyRole("ENTREPRISE", "RESPONSABLERH")
                .requestMatchers("/api/offresEmploi/byId/**").permitAll()
                .requestMatchers("/api/offresEmploi/updateDateExpiration/**").hasAnyRole("ENTREPRISE", "RESPONSABLERH")
                .requestMatchers("/api/offresEmploi/delete/**").hasAnyRole("ENTREPRISE", "RESPONSABLERH")

                .requestMatchers("api/responsablesRH/**").permitAll()
                .anyRequest().permitAll()) // raja3ha authenticated bch tmchi lform login mrigla sinn kani
                                           // .permitAll() raw les routes mahloulin
        // .httpBasic(Customizer.withDefaults())
        .formLogin(form -> form.loginProcessingUrl("/login").successHandler(successHandler).permitAll())
        // .httpBasic(httpBasic -> {
        // })
        // .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
        .logout(
            logout -> logout.logoutUrl("/logout").logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll())
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return httpSecurity.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
