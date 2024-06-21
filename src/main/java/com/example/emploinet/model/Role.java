package com.example.emploinet.model;

import org.apache.tomcat.util.openssl.pem_password_cb;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="roles")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role {
  @Id
  private String id;
  private String name;
}
