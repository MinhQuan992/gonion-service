package com.gonion.identity.framework.api;

import com.gonion.identity.framework.dto.GeneralResponse;
import com.gonion.identity.framework.dto.passwordresetting.ResetPasswordRequest;
import com.gonion.identity.framework.dto.passwordresetting.VerifyEmailRequest;
import com.gonion.identity.framework.dto.passwordresetting.VerifyTokenRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/reset-password")
public interface PasswordResettingApi {
  @PostMapping("/verify-email")
  ResponseEntity<GeneralResponse> verifyEmail(@RequestBody @Valid VerifyEmailRequest request);

  @PostMapping("/verify-token")
  ResponseEntity<GeneralResponse> verifyToken(@RequestBody @Valid VerifyTokenRequest request);

  @PutMapping("/reset")
  ResponseEntity<GeneralResponse> resetPassword(@RequestBody @Valid ResetPasswordRequest request);
}
