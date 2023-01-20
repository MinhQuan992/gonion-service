package com.gonion.identity.framework.api;

import com.gonion.identity.framework.dto.GeneralResponse;
import com.gonion.identity.framework.dto.UserResponse;
import com.gonion.identity.framework.dto.profile.ChangeInfoRequest;
import com.gonion.identity.framework.dto.profile.ChangePasswordRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/profile")
public interface ProfileApi {
  @GetMapping
  ResponseEntity<UserResponse> getCurrentUser();

  @PutMapping("/change-password")
  ResponseEntity<GeneralResponse> changePassword(@RequestBody @Valid ChangePasswordRequest request);

  @PutMapping("/change-info")
  ResponseEntity<UserResponse> changeInfo(@RequestBody @Valid ChangeInfoRequest request);
}
