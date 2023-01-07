package com.gonion.identity.service;

import com.gonion.identity.common.ProviderType;
import com.gonion.identity.common.Role;
import com.gonion.identity.config.JwtService;
import com.gonion.identity.entity.User;
import com.gonion.identity.exception.EmailAlreadyExistsException;
import com.gonion.identity.framework.dto.UserResponse;
import com.gonion.identity.framework.dto.authentication.AuthenticationRequest;
import com.gonion.identity.framework.dto.authentication.AuthenticationResponse;
import com.gonion.identity.framework.dto.authentication.RegisterRequest;
import com.gonion.identity.mapper.UserMapper;
import com.gonion.identity.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public UserResponse register(RegisterRequest request) {
    Optional<User> userOptional = userRepository.findUserByEmail(request.getEmail().trim());
    if (userOptional.isPresent()) {
      throw new EmailAlreadyExistsException("This email already exists.");
    }

    User user = User.builder()
        .fullName(request.getFullName().trim())
        .email(request.getEmail().trim())
        .password(passwordEncoder.encode(request.getPassword()))
        .providerType(ProviderType.LOCAL)
        .role(Role.USER)
        .build();

    return UserMapper.INSTANCE.userToUserDto(userRepository.save(user));
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    User user = userRepository.findUserByEmail(request.getEmail()).orElseThrow();
    String jwt = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwt)
        .build();
  }
}
