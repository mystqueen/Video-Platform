package org.rossie.videoPlatform.Service;

import org.rossie.videoPlatform.dto.*;
import org.rossie.videoPlatform.model.User;

import java.util.UUID;

public interface UserService {

    Object addNewUser(User newUser);

    Object verifyEmail(UUID authToken);

    Object login(UserLoginDto userLoginDto);

    Object getEmail(String email);

    Object resetPassword(ResetPasswordDto resetPasswordDto);

    Object requestPasswordReset(ResetPasswordRequestDto resetPasswordRequestDto);

    Object deleteUser(UserLoginDto userLoginDto);

    Object resendVerificationToken(String email);
}
