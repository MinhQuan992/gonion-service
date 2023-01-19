package com.gonion.identity.service;

import com.gonion.identity.entity.User;
import com.gonion.identity.exception.WrongPasswordException;
import com.gonion.identity.framework.dto.GeneralResponse;
import com.gonion.identity.framework.dto.profile.ChangePasswordRequest;
import com.gonion.identity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
  private final BaseService baseService;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  public GeneralResponse changePassword(ChangePasswordRequest request) {
    User user = baseService.getCurrentUser();
    if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
      throw new WrongPasswordException("The oldPassword is incorrect.");
    }

    user.setPassword(passwordEncoder.encode(request.getNewPassword()));
    userRepository.save(user);
    return GeneralResponse.builder()
        .message("The password has been changed.")
        .build();
  }
}
