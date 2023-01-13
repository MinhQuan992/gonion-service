package com.gonion.identity.repository;

import com.gonion.identity.entity.PasswordResetToken;
import com.gonion.identity.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, UUID> {
  Optional<PasswordResetToken> findByUser(User user);
}
