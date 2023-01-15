package com.gonion.identity.framework.dto.passwordresetting;

import com.gonion.identity.common.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequest {
  @NotBlank(message = "The token is required.")
  private String token;

  @NotBlank(message = "The newPassword is required.")
  @Pattern(
      regexp = Constants.PASSWORD_PATTERN,
      message = "The newPassword must contain at least one lowercase letter, one uppercase letter and one number.")
  @Size(
      min = 8,
      max = 20,
      message = "The length of the newPassword must be between 8 and 20 characters.")
  private String newPassword;
}
