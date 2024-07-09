package org.rossie.videoPlatform.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.rossie.videoPlatform.dto.*;
import org.rossie.videoPlatform.exception.EntityExistsException;
import org.rossie.videoPlatform.exception.EntityNotFoundException;
import org.rossie.videoPlatform.exception.TokenExpiredException;
import org.rossie.videoPlatform.model.Admin;
import org.rossie.videoPlatform.model.User;
import org.rossie.videoPlatform.model.Video;
import org.rossie.videoPlatform.repository.AdminRepository;
import org.rossie.videoPlatform.repository.UserRepository;
import org.rossie.videoPlatform.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;
    @Autowired
    @Lazy
    private VideoService videoService;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    @Override
    public Object addNewUser(Admin newAdmin) {
        Optional<Admin> userByEmail = adminRepository.findUserByEmail(newAdmin.getEmail());
        if (userByEmail.isPresent()) {
            System.out.println("The email address is already in use by another account.");
            throw new EntityExistsException("The email address is already in use by another account.");
        }
        newAdmin.setAuthToken(UUID.randomUUID());
        newAdmin.setAuthTokenExpire(LocalDateTime.now().plusMinutes(30));
        sendVerificationEmail(newAdmin);
        newAdmin.setCreatedAt(Instant.now());

        Admin admin = adminRepository.save(newAdmin);
        System.out.println("-----------------Registration Successful--------------------");
        return  objectMapper.convertValue(admin, UserResponseDto.class);
    }

    @Override
    public Object verifyEmail(UUID authToken) {
        Optional<Admin> admin = adminRepository.findByAuthToken(authToken);
        if (admin.isEmpty()){
            throw new EntityNotFoundException("Invalid Token");
        }
        if (admin.get().getAuthTokenExpire().isBefore(LocalDateTime.now())) {
            admin.get().setAccountVerified(false);
            throw new TokenExpiredException("Token Expired");
        }
        admin.get().setAccountVerified(true);
        admin.get().setSessionToken(UUID.randomUUID());
        admin.get().setSessionTokenExpire(LocalDateTime.now().plusDays(30));
        adminRepository.save(admin.get());
        System.out.println("------------Email Verified---------------");
        return "Email Verified";
    }

    @Override
    public Object login(UserLoginDto userLoginDto) {
        Optional<Admin> admin = adminRepository.findUserByEmail(userLoginDto.getEmail());
        if (admin.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
        if (admin.get().getPassword().equals(userLoginDto.getPassword())) {
            System.out.println("------------------- Login Successful -----------");
            return admin;
        } else {
            throw new EntityNotFoundException("Invalid Password");
        }
    }

    @Override
    public Object resendVerificationToken(String email) {
        Optional<Admin> adminOptional = adminRepository.findUserByEmail(email);
        if (adminOptional.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }

        Admin admin = adminOptional.get();
        admin.setAuthToken(UUID.randomUUID());
        admin.setAuthTokenExpire(LocalDateTime.now().plusMinutes(30));
        sendVerificationEmail(admin);
        adminRepository.save(admin);
        return null;
    }



    private void sendVerificationEmail(Admin newAdmin) {
        emailService.sendMail(
                newAdmin.getEmail(),
                "queenefya669@gmail.com",
                "VideoPlatform: Verify Your Email",
                "Dear " + newAdmin.getUsername() + ", \n \n Kindly use the one time token below to verify your email. \n \n One Time Token: " + newAdmin.getAuthToken()
        );
    }

    public Object requestPasswordReset(ResetPasswordRequestDto resetPasswordRequestDto) {
        Optional<Admin> admin = adminRepository.findUserByEmail(resetPasswordRequestDto.getEmail());
        if (admin.isEmpty()) {
            throw new EntityNotFoundException("User with this email does not exist.");
        }
        Admin existingAdmin = admin.get();
        existingAdmin.setResetToken(UUID.randomUUID());
        existingAdmin.setResetTokenExpire(LocalDateTime.now().plusMinutes(30));
        adminRepository.save(existingAdmin);

        sendResetEmail(existingAdmin);

        return "Password reset email sent.";
    }


    public Object resetPassword(ResetPasswordDto resetPasswordDto) {
        Optional<Admin> admin = adminRepository.findByResetToken(resetPasswordDto.getResetToken());
        if (admin.isEmpty() || admin.get().getResetTokenExpire().isBefore(LocalDateTime.now())) {
            throw new EntityNotFoundException("Invalid or expired token.");
        }

        Admin existingAdmin = admin.get();
        existingAdmin.setPassword(resetPasswordDto.getNewPassword());
        adminRepository.save(existingAdmin);

        return "Password reset successful.";
    }

    private void sendResetEmail(Admin admin) {
        String resetLink = "http://localhost:8080/api/v1/password-reset/" + admin.getResetToken();
        emailService.sendMail(
                admin.getEmail(),
                "queenefya669@gmail.com",
                "VideoPlatform: Password Reset Request",
                "Dear " + admin.getUsername() + ", \n \n Please click on the link below to reset your password: <a href=\"" + resetLink + "\">Reset Password</a>"
        );
    }

    @Override
    public Object deleteAdmin(Admin admin) {
        Optional<Admin> adminByEmail = adminRepository.findUserByEmail(admin.getEmail());
        if (adminByEmail.isEmpty()) {
            throw new EntityNotFoundException("User with this email does not exist.");
        }
            adminRepository.deleteByEmail(admin.getEmail());
        return "Account deleted";
    }

    @Override
    public Object deleteUser(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        if (userByEmail.isEmpty()) {
            throw new EntityNotFoundException("User with this email does not exist.");
        }
        userRepository.deleteByEmail(user.getEmail());
        return "Account deleted";
    }

    @Override
    public Object getVideoLink(Long videoId){
        VideoResponseDto videoResponseDto = new VideoResponseDto();
        videoService.getVideoLink(videoResponseDto.getId());
        videoResponseDto.setUrl("http://localhost.com:8080/api/v1/video/" + videoResponseDto.getId());
        return videoResponseDto;
    }

    @Override
    public Object deleteVideo(Long videoId) {
        Optional<Video> video = videoRepository.findById(videoId);
        if (video.isEmpty()) {
            throw new EntityNotFoundException("Video with this id does not exist.");
        }
        videoRepository.delete(video.get());
        return "Video deleted";
    }

    @Override
    public List<VideoResponseDto> getAllVideos() {
        return objectMapper.convertValue(videoRepository.findAll(), new TypeReference<List<VideoResponseDto>>() {
        });
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return objectMapper.convertValue(userRepository.findAll(), new TypeReference<List<UserResponseDto>>() {
        });
    }

}
