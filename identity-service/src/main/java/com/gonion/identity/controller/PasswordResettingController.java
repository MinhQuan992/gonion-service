package com.gonion.identity.controller;

import com.gonion.identity.framework.api.PasswordResettingApi;
import com.gonion.identity.framework.dto.GeneralResponse;
import com.gonion.identity.framework.dto.passwordresetting.VerifyEmailRequest;
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
}
