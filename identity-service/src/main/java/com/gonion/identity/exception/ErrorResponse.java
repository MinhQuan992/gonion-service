package com.gonion.identity.exception;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
  private UUID id = UUID.randomUUID();
  private String message;

  public ErrorResponse() {}

  public ErrorResponse(String message) {
    this.message = message;
  }
}
