package com.example.emploinet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class FileTypeNotAllowedException extends RuntimeException {
  public FileTypeNotAllowedException(String message) {
    super(message);
  }
}
