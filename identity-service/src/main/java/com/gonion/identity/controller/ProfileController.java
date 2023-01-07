package com.gonion.identity.controller;

import com.gonion.identity.framework.api.ProfileApi;
import com.gonion.identity.framework.dto.UserResponse;
import com.gonion.identity.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController implements ProfileApi {
  private final BaseService baseService;

  @Override
  public ResponseEntity<UserResponse> getCurrentUser() {
    return ResponseEntity.ok(baseService.getCurrentUserDto());
  }
}
