package com.gonion.identity.controller;

import com.gonion.identity.framework.api.AuthenticationApi;
import com.gonion.identity.framework.dto.UserResponse;
import com.gonion.identity.framework.dto.authentication.AuthenticationRequest;
import com.gonion.identity.framework.dto.authentication.AuthenticationResponse;
import com.gonion.identity.framework.dto.authentication.RegisterRequest;
import com.gonion.identity.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationApi {
  private final AuthenticationService authenticationService;

  @Override
  public ResponseEntity<UserResponse> register(RegisterRequest request) {
    return ResponseEntity.ok(authenticationService.register(request));
  }

  @Override
  public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request) {
    return ResponseEntity.ok(authenticationService.authenticate(request));
  }
}
