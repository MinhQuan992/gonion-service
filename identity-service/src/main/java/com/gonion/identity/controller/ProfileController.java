package com.gonion.identity.controller;

import com.gonion.identity.framework.api.ProfileApi;
import com.gonion.identity.framework.dto.GeneralResponse;
import com.gonion.identity.framework.dto.UserResponse;
import com.gonion.identity.framework.dto.profile.ChangeInfoRequest;
import com.gonion.identity.framework.dto.profile.ChangePasswordRequest;
import com.gonion.identity.service.BaseService;
import com.gonion.identity.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController implements ProfileApi {
  private final BaseService baseService;
  private final ProfileService profileService;

  @Override
  public ResponseEntity<UserResponse> getCurrentUser() {
    return ResponseEntity.ok(baseService.getCurrentUserDto());
  }

  @Override
  public ResponseEntity<GeneralResponse> changePassword(ChangePasswordRequest request) {
    return ResponseEntity.ok(profileService.changePassword(request));
  }

  @Override
  public ResponseEntity<UserResponse> changeInfo(ChangeInfoRequest request) {
    return ResponseEntity.ok(profileService.changeInfo(request));
  }
}
