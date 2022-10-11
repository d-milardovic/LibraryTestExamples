package com.appLabIT.onlineLibrary.controller;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;

@ControllerAdvice
public class ControllerExceptionHandler {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @ExceptionHandler(value = {IllegalArgumentException.class})
  public ResponseEntity<JsonNode> resourceNotFoundException(IllegalArgumentException ex, WebRequest request) {
    ObjectNode jsonResponse = OBJECT_MAPPER.createObjectNode().put("error", ex.getMessage());
    return new ResponseEntity<>(jsonResponse, HttpStatus.BAD_REQUEST);
  }

/*
  @ExceptionHandler(value = {IllegalArgumentException.class})
  public ResponseEntity<String> resourceNotFoundException(IllegalArgumentException ex, WebRequest request) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }*/
}
