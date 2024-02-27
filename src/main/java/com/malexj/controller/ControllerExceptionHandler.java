package com.malexj.controller;

import com.malexj.exception.TelegramApiServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler({TelegramApiServiceException.class})
  public ResponseEntity<Object> handleStudentNotFoundException(
      TelegramApiServiceException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }
}
