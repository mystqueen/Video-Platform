package org.rossie.videoPlatform.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.rossie.videoPlatform.controller.ResponseHandler;
import org.rossie.videoPlatform.dto.ResetPasswordDto;
import org.rossie.videoPlatform.dto.ResetPasswordRequestDto;
import org.rossie.videoPlatform.dto.UserLoginDto;
import org.rossie.videoPlatform.dto.UserResponseDto;
import org.rossie.videoPlatform.exception.EntityExistsException;
import org.rossie.videoPlatform.exception.EntityNotFoundException;
import org.rossie.videoPlatform.exception.TokenExpiredException;
import org.rossie.videoPlatform.model.User;
import org.rossie.videoPlatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Component
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Object addNewUser(User newUser) {
        Optional<User> userByEmail = userRepository.findUserByEmail(newUser.getEmail());
        if (userByEmail.isPresent()) {
            System.out.println("The email address is already in use by another account.");
            throw new EntityExistsException("The email address is already in use by another account.");
        }
        newUser.setAuthToken(UUID.randomUUID());
        newUser.setAuthTokenExpire(LocalDateTime.now().plusMinutes(30));
        sendVerificationEmail(newUser);
        newUser.setCreatedAt(Instant.now());

        User user = userRepository.save(newUser);
        System.out.println("-----------------Registration Successful--------------------");
        return  objectMapper.convertValue(user, UserResponseDto.class);
    }

    @Override
    public Object verifyEmail(UUID authToken) {
        Optional<User> user = userRepository.findByAuthToken(authToken);
        if (user.get().getAuthTokenExpire().isBefore(LocalDateTime.now())) {
            user.get().setAccountVerified(false);
            throw new TokenExpiredException("Token Expired");
        }
        userRepository.save(user.get());
        System.out.println("------------Email Verified---------------");
        return "Email Verified";
    }

    @Override
    public Object login(UserLoginDto userLoginDto) {
        Optional<User> user = userRepository.findUserByEmail(userLoginDto.getEmail());
        if (user.get().getPassword().equals(userLoginDto.getPassword())) {
            System.out.println("------------------- Login Successful -----------");
            return "Login Successful";
        } else {
            throw new EntityNotFoundException("Invalid Password");
        }
    }

    @Override
    public Object getEmail(String email) {
        return email;
    }

    private Optional<User> setAuthToken(User newUser) {
        newUser.setAuthToken(UUID.randomUUID());
        newUser.setAuthTokenExpire(LocalDateTime.now().plusMinutes(30));
        return Optional.of(userRepository.save(newUser));
    }

    private void sendVerificationEmail(User newUser) {
        emailService.sendMail(
                newUser.getEmail(),
                "queenefya669@gmail.com",
                "VideoPlatform: Verify Your Email",
                "Dear " + newUser.getUsername() + ", \n \n Please click on the link below to verify your account: <a href=\"http://localhost:8080/api/v1/user/verify-email/" + newUser.getAuthToken() + "\">Verify Email</a>"
        );
    }

    public Object requestPasswordReset(ResetPasswordRequestDto resetPasswordRequestDto) {
        Optional<User> user = userRepository.findUserByEmail(resetPasswordRequestDto.getEmail());
        if (user.isEmpty()) {
            throw new EntityNotFoundException("User with this email does not exist.");
        }
        User existingUser = user.get();
        existingUser.setResetToken(UUID.randomUUID());
        existingUser.setResetTokenExpire(LocalDateTime.now().plusMinutes(30));
        userRepository.save(existingUser);

        sendResetEmail(existingUser);

        return "Password reset email sent.";
    }

    public Object resetPassword(ResetPasswordDto resetPasswordDto) {
        Optional<User> user = userRepository.findByResetToken(resetPasswordDto.getResetToken());
        if (user.isEmpty() || user.get().getResetTokenExpire().isBefore(LocalDateTime.now())) {
            throw new EntityNotFoundException("Invalid or expired token.");
        }

        User existingUser = user.get();
        existingUser.setPassword(resetPasswordDto.getNewPassword());
        userRepository.save(existingUser);

        return "Password reset successful.";
    }

    private void sendResetEmail(User user) {
        String resetLink = "http://localhost:8080/api/v1/password-reset/" + user.getResetToken();
        emailService.sendMail(
                user.getEmail(),
                "queenefya669@gmail.com",
                "VideoPlatform: Password Reset Request",
                "Dear " + user.getUsername() + ", \n \n Please click on the link below to reset your password: <a href=\"" + resetLink + "\">Reset Password</a>"
        );
    }


}
