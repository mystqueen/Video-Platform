package org.rossie.videoPlatform.Service;

import org.rossie.videoPlatform.dto.*;
import org.rossie.videoPlatform.model.Admin;
import org.rossie.videoPlatform.model.User;
import org.rossie.videoPlatform.model.Video;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface AdminService {

    Object addNewUser(Admin newAdmin);

    Object verifyEmail(UUID authToken);

    Object login(UserLoginDto userLoginDto);

//    Object getEmail(String email);

    Object resetPassword(ResetPasswordDto resetPasswordDto);

    Object requestPasswordReset(ResetPasswordRequestDto resetPasswordRequestDto);

    Object deleteAdmin(Admin admin);

    Object deleteUser(User user);

    Object getVideoLink(Long videoId);

    Object deleteVideo(Long videoId);

    Object resendVerificationToken(String email);

    Object getAllUsers();

    List<VideoResponseDto> getAllVideos();
}
