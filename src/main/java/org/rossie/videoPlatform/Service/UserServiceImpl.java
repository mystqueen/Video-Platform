package org.rossie.videoPlatform.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.rossie.videoPlatform.dto.UserResponseDto;
import org.rossie.videoPlatform.exception.EntityExistsException;
import org.rossie.videoPlatform.model.User;
import org.rossie.videoPlatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        if (user.get().getAuthTokenExpire().isAfter(LocalDateTime.now())){
            user.get().setAccountVerified(true);
            System.out.println("Token has expired");
        }
        userRepository.save(user.get());
        return "Email Verified";
    }

    private void sendVerificationEmail(User newUser) {
        emailService.sendMail(
                newUser.getEmail(),
                "queenefya669@gmail.com",
                "VideoPlatform: Verify Your Email",
                "Dear " + newUser.getUsername() + ", \n \n Please click on the link below to verify your account: <a href=\"http://localhost:8080/api/v1/user/verify-email/" + newUser.getAuthToken() + "\">Verify Email</a>"
        );
    }


}
