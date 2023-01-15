package com.gonion.identity.controller;

import com.gonion.identity.framework.api.PasswordResettingApi;
import com.gonion.identity.framework.dto.GeneralResponse;
import com.gonion.identity.framework.dto.passwordresetting.ResetPasswordRequest;
import com.gonion.identity.framework.dto.passwordresetting.VerifyEmailRequest;
import com.gonion.identity.framework.dto.passwordresetting.VerifyTokenRequest;
import com.gonion.identity.service.PasswordResettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PasswordResettingController implements PasswordResettingApi {
  private final PasswordResettingService passwordResettingService;

  @Override
  public ResponseEntity<GeneralResponse> verifyEmail(VerifyEmailRequest request) {
    return new ResponseEntity<>(passwordResettingService.verifyEmail(request), HttpStatus.ACCEPTED);
  }

  @Override
  public ResponseEntity<GeneralResponse> verifyToken(VerifyTokenRequest request) {
    return ResponseEntity.ok(passwordResettingService.verifyToken(request));
  }

  @Override
  public ResponseEntity<GeneralResponse> resetPassword(ResetPasswordRequest request) {
    return ResponseEntity.ok(passwordResettingService.resetPassword(request));
  }
}
