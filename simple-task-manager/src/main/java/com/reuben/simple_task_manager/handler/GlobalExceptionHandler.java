package com.reuben.simple_task_manager.handler;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.reuben.simple_task_manager.controllers.AuthController;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class GlobalExceptionHandler {

private static final Logger logger = LogManager.getLogger(AuthController.class);


  @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
      var errors = new HashMap<String, String>();
      exp.getBindingResult().getAllErrors().forEach(error -> {
        var fieldName = ((FieldError) error).getField();
        var errorMessage = fieldName + " "+ error.getDefaultMessage();
        errors.put(fieldName, errorMessage);
      });
      logger.error("Validation error:", errors);
      return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException exp) {
    var errors = new HashMap<String, String>();
    errors.put("error", exp.getLocalizedMessage());
    logger.error("Illegal argument error:", exp);
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<?> handleAuthenticationException(AuthenticationException exp) {
    var errors = new HashMap<String, String>();
    errors.put("error", exp.getLocalizedMessage());
    logger.error("Authentication error:", exp);
    return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException exp) {
    var errors = new HashMap<String, String>();
    errors.put("error", exp.getLocalizedMessage());
    logger.error("User not found error:", exp);
    return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ExpiredJwtException.class)
  public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException exp) {
    var errors = new HashMap<String, String>();
    errors.put("error", "JWT token has expired");
    logger.error("JWT token expired error:", exp);
    return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleException(Exception exp) {
    var errors = new HashMap<String, String>();
    errors.put("error", exp.getLocalizedMessage());
    logger.error("Internal server error:", exp);
    return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
