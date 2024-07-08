package org.rossie.videoPlatform.Service;

import org.rossie.videoPlatform.dto.ResetPasswordDto;
import org.rossie.videoPlatform.dto.ResetPasswordRequestDto;
import org.rossie.videoPlatform.dto.UserLoginDto;
import org.rossie.videoPlatform.dto.VideoDto;
import org.rossie.videoPlatform.model.Admin;
import org.rossie.videoPlatform.model.User;
import org.rossie.videoPlatform.model.Video;

import java.io.IOException;
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

//    Object uploadVideo(Video video) throws IOException;

    Object getVideoLink(Long videoId);

    Object deleteVideo(Long videoId);

    Object getAllVideos();

    Object resendVerificationToken(String email);

    Object getAllUsers();
}
