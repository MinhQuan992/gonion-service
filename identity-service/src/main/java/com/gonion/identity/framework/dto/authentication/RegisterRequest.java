package com.gonion.identity.framework.dto.authentication;

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
public class RegisterRequest {
  @NotBlank(message = "The fullName is required.")
  private String fullName;

  @NotBlank(message = "The email is required.")
  private String email;

  @NotBlank(message = "The password is required.")
  @Pattern(
      regexp = Constants.PASSWORD_PATTERN,
      message = "The password must contain at least one lowercase letter, one uppercase letter and one number.")
  @Size(
      min = 8,
      max = 20,
      message = "The length of the password must be between 8 and 20 characters.")
  private String password;
}
