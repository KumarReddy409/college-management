package com.student.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class StudentExceptionHandler {


   @ExceptionHandler(StudentRequestException.class)
   public ResponseEntity<Object> handleStudentRequestException(
           StudentRequestException studentRequestException) {
      HttpStatus badRequest = HttpStatus.BAD_REQUEST;
      StudentException studentException = new StudentException(badRequest,
              studentRequestException.getMessage());
      return new ResponseEntity<>(studentException, badRequest);
   }

   @ExceptionHandler(StudentAPIException.class)
   public ResponseEntity<Object> handleStudentAPIException(
           StudentAPIException studentAPIException) {
      HttpStatus badRequest = studentAPIException.getHttpStatus();
      StudentException studentException = new StudentException(badRequest,
              studentAPIException.getMessage());
      return new ResponseEntity<>(studentException, badRequest);
   }

   @ExceptionHandler(MethodArgumentNotValidException.class)
   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

      Map<String, String> errors = new HashMap<>();
      ex.getBindingResult().getAllErrors().forEach(error -> {

         String fieldName = ((FieldError) error).getField();
         String message = error.getDefaultMessage();
         errors.put(fieldName, message);
      });
      return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler(ConstraintViolationException.class)
   public ResponseEntity<Object> constraintViolationExceptionHandler(ConstraintViolationException exception) {
      Map<String, String> errorsMap = new HashMap<>();
      String[] errors = exception.getMessage().split(",");
      Arrays.stream(errors).forEach(error -> {
         String[] individualError = error.split(":");
         errorsMap.put(individualError[0].split("\\.")[1], individualError[1]);
      });
      return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler(MissingServletRequestParameterException.class)
   public ResponseEntity<Object> missingServletRequestParameterException(MissingServletRequestParameterException exception) {
      Map<String, String> errors = new HashMap<>();
      errors.put(exception.getParameterName(), exception.getMessage());
      return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
   }
}
