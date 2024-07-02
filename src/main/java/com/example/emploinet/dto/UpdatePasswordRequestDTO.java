package com.example.emploinet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordRequestDTO {
  private String email;
  private String password;
}
