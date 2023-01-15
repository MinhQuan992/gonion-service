package com.gonion.identity.framework.dto.passwordresetting;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerifyTokenRequest {
  @NotBlank(message = "The token is required.")
  private String token;
}
