package com.bravo.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Data Not Found")
public class DataNotFoundException extends RuntimeException {

  public DataNotFoundException(final String message){
    super(String.format("DataNotFoundException | %s", message));
  }
}
