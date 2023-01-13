package com.gonion.identity.framework.api;

import com.gonion.identity.framework.dto.GeneralResponse;
import com.gonion.identity.framework.dto.passwordresetting.VerifyEmailRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/reset-password")
public interface PasswordResettingApi {
  @PostMapping("/verify-email")
  ResponseEntity<GeneralResponse> verifyEmail(@RequestBody @Valid VerifyEmailRequest request);
}
