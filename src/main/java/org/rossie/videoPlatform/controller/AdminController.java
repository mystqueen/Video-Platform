package org.rossie.videoPlatform.controller;

import lombok.RequiredArgsConstructor;
import org.rossie.videoPlatform.Service.AdminService;
import org.rossie.videoPlatform.Service.UserService;
import org.rossie.videoPlatform.dto.ResetPasswordDto;
import org.rossie.videoPlatform.dto.ResetPasswordRequestDto;
import org.rossie.videoPlatform.dto.UserLoginDto;
import org.rossie.videoPlatform.model.Admin;
import org.rossie.videoPlatform.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/")
public class AdminController {

    @Autowired
    private AdminService adminService;


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

    @PostMapping("v1/admin/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> login(@RequestBody UserLoginDto userLoginDto) {
        return ResponseHandler.success(adminService.login(userLoginDto), "Login Successful", HttpStatus.OK);
    }

    @PostMapping("v1/admin/reset-password")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Object> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto){
        return ResponseHandler.success(adminService.resetPassword(resetPasswordDto), "Password Reset Successful", HttpStatus.ACCEPTED);
    }

    @PostMapping("v1/admin/password-request")
    public ResponseEntity<Object> requestPasswordReset(@RequestBody ResetPasswordRequestDto resetPasswordRequestDto) {
        return ResponseHandler.success(adminService.requestPasswordReset(resetPasswordRequestDto),"Password Reset Successful", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("v1/admin/delete-admin")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> deleteAdmin(@RequestParam Admin admin) {
        return ResponseHandler.success(adminService.deleteAdmin(admin), "Account deleted", HttpStatus.OK);
    }

    @DeleteMapping("v1/admin/delete-user")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> deleteUser(@RequestParam User user) {
        return ResponseHandler.success(adminService.deleteUser(user), "Account deleted", HttpStatus.OK);
    }
}