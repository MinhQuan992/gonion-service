package com.gonion.identity.framework.api;

import com.gonion.identity.framework.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/profile")
public interface ProfileApi {
  @GetMapping
  ResponseEntity<UserResponse> getCurrentUser();
}
