package com.example.emploinet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.emploinet.repository.RoleRepository;
import com.example.emploinet.repository.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;


}
