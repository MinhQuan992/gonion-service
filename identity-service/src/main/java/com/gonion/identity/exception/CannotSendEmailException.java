package com.gonion.identity.exception;

public class CannotSendEmailException extends RuntimeException {
  public CannotSendEmailException(String message) {
    super(message);
  }
}
