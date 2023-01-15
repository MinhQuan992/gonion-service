package com.gonion.identity.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.gonion.identity.common.Constants;
import com.gonion.identity.entity.Mail;
import com.gonion.identity.entity.PasswordResetToken;
import com.gonion.identity.entity.User;
import com.gonion.identity.exception.CannotSendEmailException;
import com.gonion.identity.exception.ExpiredResetTokenException;
import com.gonion.identity.exception.InvalidResetTokenException;
import com.gonion.identity.exception.NotFoundException;
import com.gonion.identity.framework.dto.GeneralResponse;
import com.gonion.identity.framework.dto.passwordresetting.ResetPasswordRequest;
import com.gonion.identity.framework.dto.passwordresetting.VerifyEmailRequest;
import com.gonion.identity.framework.dto.passwordresetting.VerifyTokenRequest;
import com.gonion.identity.repository.PasswordResetTokenRepository;
import com.gonion.identity.repository.UserRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PasswordResettingService {
  private final UserRepository userRepository;
  private final PasswordResetTokenRepository passwordResetTokenRepository;
  private final PasswordEncoder passwordEncoder;

  @Value("${value.aws-access-key-id}")
  private String awsAccessKeyId;

  @Value("${value.aws-secret-access-key}")
  private String awsSecretAccessKey;

  @Value("${value.front-end-base-url}")
  private String frontEndBaseUrl;

  public GeneralResponse verifyEmail(VerifyEmailRequest request) {
    User user = userRepository.findUserByEmail(request.getEmail())
        .orElseThrow(() -> new NotFoundException("Email not found."));
    PasswordResetToken resetToken = generateResetToken(user);
    sendTokenToEmail(user.getEmail(), user.getFullName(), resetToken.getToken());
    return GeneralResponse.builder()
        .message("A reset link has been given to your email.")
        .build();
  }

  private PasswordResetToken generateResetToken(User user) {
    Optional<PasswordResetToken> resetTokenOptional = passwordResetTokenRepository.findByUser(user);
    if (resetTokenOptional.isEmpty()) {
      return createNewResetToken(user);
    }
    return updateCurrentResetToken(resetTokenOptional.get());
  }

  private PasswordResetToken createNewResetToken(User user) {
    String tokenString = UUID.randomUUID().toString();
    Date expiryTime = new Date(System.currentTimeMillis() + Constants.RESET_TOKEN_EXPIRATION_TIME);

    PasswordResetToken resetToken = PasswordResetToken.builder()
        .token(tokenString)
        .user(user)
        .expiryTime(expiryTime)
        .build();

    return passwordResetTokenRepository.save(resetToken);
  }

  private PasswordResetToken updateCurrentResetToken(PasswordResetToken passwordResetToken) {
    Date expiryTime = new Date(System.currentTimeMillis() + Constants.RESET_TOKEN_EXPIRATION_TIME);
    passwordResetToken.setToken(UUID.randomUUID().toString());
    passwordResetToken.setExpiryTime(expiryTime);
    return passwordResetTokenRepository.save(passwordResetToken);
  }

  private void sendTokenToEmail(String email, String fullName, String resetToken) {
    Mail mail = Mail.builder()
        .mailFrom(Constants.SENDER_EMAIL)
        .mailTo(email)
        .mailSubject("Reset Your Password")
        .mailContent(createMailContent(fullName, resetToken))
        .build();
    try {
      log.info("Sending email with the reset link to {}", email);
      AWSCredentials credentials =
          new BasicAWSCredentials(awsAccessKeyId, awsSecretAccessKey);
      AmazonSimpleEmailService client =
          AmazonSimpleEmailServiceClientBuilder.standard()
              .withRegion(Regions.US_EAST_1)
              .withCredentials(new AWSStaticCredentialsProvider(credentials))
              .build();
      SendEmailRequest request =
          new SendEmailRequest()
              .withDestination(new Destination().withToAddresses(mail.getMailTo()))
              .withMessage(
                  new Message()
                      .withBody(
                          new Body()
                              .withHtml(
                                  new Content()
                                      .withCharset("UTF-8")
                                      .withData(mail.getMailContent())))
                      .withSubject(
                          new Content().withCharset("UTF-8").withData(mail.getMailSubject())))
              .withSource(mail.getMailFrom());
      client.sendEmail(request);
      log.info("Email sent");
    } catch (Exception ex) {
      log.error("The email was not sent. Error message: {}", ex.getMessage());
      throw new CannotSendEmailException("Error occurred when sending email.");
    }
  }

  private String createMailContent(String fullName, String token) {
    String mailTemplate = """
        Hi %s,
        <br/>
        <br/>
        Please open this link to reset your password: %s/reset-password?token=%s.
        <br/>
        This link will be expired after 10 minutes. If it is expired, you must get another link.
        <br/>
        <br/>
        If you didn't ask to reset your password, you can safely ignore this email.
        <br/>
        <br/>
        Best regards,
        <br/>
        The GoNion Team
        """;
    return String.format(mailTemplate, fullName, frontEndBaseUrl, token);
  }

  public GeneralResponse verifyToken(VerifyTokenRequest request) {
    PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(request.getToken())
        .orElseThrow(() -> new InvalidResetTokenException("This token is invalid."));
    verifyExpiration(resetToken);
    return GeneralResponse.builder()
        .message("This token is OK.")
        .build();
  }

  public GeneralResponse resetPassword(ResetPasswordRequest request) {
    PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(request.getToken())
        .orElseThrow(() -> new InvalidResetTokenException("This token is invalid."));
    verifyExpiration(resetToken);

    User user = resetToken.getUser();
    user.setPassword(passwordEncoder.encode(request.getNewPassword()));
    userRepository.save(user);

    return GeneralResponse.builder()
        .message("The password has been reset.")
        .build();
  }

  private void verifyExpiration(PasswordResetToken resetToken) {
    Calendar calendar = Calendar.getInstance();
    if (resetToken.getExpiryTime().before(calendar.getTime())) {
      throw new ExpiredResetTokenException("This token is expired.");
    }
  }
}
