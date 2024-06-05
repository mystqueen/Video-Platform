package org.rossie.videoPlatform.Service;

import org.rossie.videoPlatform.dto.UserDto;
import org.rossie.videoPlatform.dto.UserResponseDto;
import org.rossie.videoPlatform.model.User;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public interface UserService {

    Object addNewUser(User newUser);

    Object verifyEmail(UUID authToken);
}
