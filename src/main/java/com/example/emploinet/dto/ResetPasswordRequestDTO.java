package com.example.emploinet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRequestDTO {
  private String linkIdentifier;
  private String newPassword;
}
