package com.example.emploinet.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class User {
  @Id
  private String id;
  private String firstName;
  private String lastName;
  private String email;

  @DBRef
  private Set<Role> roles;
}

