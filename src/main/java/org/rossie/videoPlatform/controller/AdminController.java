package org.rossie.videoPlatform.controller;

import lombok.RequiredArgsConstructor;
import org.rossie.videoPlatform.Service.AdminService;
import org.rossie.videoPlatform.Service.UserService;
import org.rossie.videoPlatform.Service.VideoService;
import org.rossie.videoPlatform.dto.*;
import org.rossie.videoPlatform.model.Admin;
import org.rossie.videoPlatform.model.User;
import org.rossie.videoPlatform.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/")
public class AdminController {

    private final AdminService adminService;
    private final VideoService videoService;


    @PostMapping("v1/admin/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> signUp(@RequestBody Admin newAdmin) {
        return ResponseHandler.success(adminService.addNewUser(newAdmin), "Account Successfully Created", HttpStatus.CREATED);
    }

    @GetMapping("v1/admin/verify-email")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Object> verifyEmail(@RequestParam UUID authToken) {
        return ResponseHandler.success(adminService.verifyEmail(authToken), "Email Verified", HttpStatus.ACCEPTED);
    }

    @PutMapping("v1/admin/newToken")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Object> newToken(@RequestBody ResendTokenRequest request) {
        return ResponseHandler.success(adminService.resendVerificationToken(request.getEmail()), "Token Generated", HttpStatus.ACCEPTED);
    }

    @PostMapping("v1/admin/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> login(@RequestBody UserLoginDto userLoginDto) {
        return ResponseHandler.success(adminService.login(userLoginDto), "Login Successful", HttpStatus.OK);
    }

    @PostMapping("v1/admin/password-request")
    public ResponseEntity<Object> requestPasswordReset(@RequestBody ResetPasswordRequestDto resetPasswordRequestDto) {
        return ResponseHandler.success(adminService.requestPasswordReset(resetPasswordRequestDto),"Password Reset Successful", HttpStatus.ACCEPTED);
    }

    @PostMapping("v1/admin/reset-password")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Object> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto){
        return ResponseHandler.success(adminService.resetPassword(resetPasswordDto), "Password Reset Successful", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("v1/admin/delete-admin")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> deleteAdmin(@RequestBody Admin admin) {
        return ResponseHandler.success(adminService.deleteAdmin(admin), "Account deleted", HttpStatus.OK);
    }

    @DeleteMapping("v1/admin/delete-user")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> deleteUser(@RequestBody User user) {
        return ResponseHandler.success(adminService.deleteUser(user), "Account deleted", HttpStatus.OK);
    }

    @PostMapping("v1/admin/upload")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> uploadVideo(@RequestParam("title") String title,
                                              @RequestParam("description") String description,
                                              @RequestParam("file") MultipartFile file) throws IOException {
        try {
            String sasUrl = videoService.uploadVideo(file, title, description);
            return ResponseHandler.success(sasUrl, "Video Uploaded Successfully", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseHandler.success(null, "Video upload failed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("v1/admin/getVideo")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getVideoLink(@RequestParam Long videoId){
        return ResponseHandler.success(adminService.getVideoLink(videoId), "Video Uploaded", HttpStatus.OK);
    }

    @DeleteMapping("v1/admin/deleteVideo")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> deleteVideo(@RequestParam Long videoId) {
        return ResponseHandler.success(adminService.deleteVideo(videoId), "Video Deleted", HttpStatus.OK);
    }

    @GetMapping("v1/admin/getVideos")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllVideos() {
        List<VideoResponseDto> videos = adminService.getAllVideos();
        return ResponseHandler.success(videos, "Videos Retrieved", HttpStatus.OK);
    }

    @GetMapping("v1/admin/getUsers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllUsers() {
        return ResponseHandler.success(adminService.getAllUsers(), "List of Customers", HttpStatus.OK);
    }
}