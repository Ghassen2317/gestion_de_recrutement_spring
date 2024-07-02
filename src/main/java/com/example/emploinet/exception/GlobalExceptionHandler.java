package com.example.emploinet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(FileTypeNotAllowedException.class)
  public ResponseEntity<String> handleFileTypeNotAllowedException(FileTypeNotAllowedException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
  }

  @ExceptionHandler(FileTooLargeException.class)
  public ResponseEntity<String> handleFileTooLargeException(FileTooLargeException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.PAYLOAD_TOO_LARGE);
  }

  @ExceptionHandler(FileNotFoundException.class)
  public ResponseEntity<String> handleFileNotFoundException(FileNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
