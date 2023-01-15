package com.gonion.identity.exception;

public class ExpiredResetTokenException extends RuntimeException {
  public ExpiredResetTokenException(String message) {
    super(message);
  }
}
