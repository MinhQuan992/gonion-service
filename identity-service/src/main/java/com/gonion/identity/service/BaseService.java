package com.gonion.identity.service;

import com.gonion.identity.entity.User;
import com.gonion.identity.framework.dto.UserResponse;
import com.gonion.identity.mapper.UserMapper;
import com.gonion.identity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseService {
  private final UserRepository userRepository;

  public User getCurrentUser() {
    return userRepository.findUserByEmail(getCurrentUserEmail()).orElseThrow();
  }

  public UserResponse getCurrentUserDto() {
    return UserMapper.INSTANCE.userToUserDto(getCurrentUser());
  }

  private String getCurrentUserEmail() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getName();
  }
}
