package com.gonion.identity.framework.dto.profile;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeInfoRequest {
  @NotBlank(message = "The fullName is required.")
  private String fullName;
}
