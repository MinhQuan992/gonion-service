package com.gonion.identity.framework.api;

import com.gonion.identity.framework.dto.UserResponse;
import com.gonion.identity.framework.dto.authentication.AuthenticationRequest;
import com.gonion.identity.framework.dto.authentication.AuthenticationResponse;
import com.gonion.identity.framework.dto.authentication.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AuthenticationApi {
  @PostMapping("/register")
  ResponseEntity<UserResponse> register(@RequestBody @Valid RegisterRequest request);

  @PostMapping("/authenticate")
  ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request);
}
