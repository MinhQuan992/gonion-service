package com.gonion.identity.framework.dto.authentication;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
  @NotBlank(message = "The email is required.")
  private String email;

  @NotBlank(message = "The password is required.")
  private String password;
}
