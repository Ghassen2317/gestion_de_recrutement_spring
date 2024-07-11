package com.example.emploinet.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private final ObjectMapper objectMapper;

  public CustomAuthenticationSuccessHandler(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {
    response.setStatus(HttpServletResponse.SC_OK);
    response.setContentType("application/json");

    Map<String, Object> data = new HashMap<>();
    data.put("message", "Authentication successful");
    data.put("user", authentication.getPrincipal());

    response.getOutputStream().println(objectMapper.writeValueAsString(data));

  }
}
