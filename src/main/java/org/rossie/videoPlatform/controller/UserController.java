package org.rossie.videoPlatform.controller;

import lombok.RequiredArgsConstructor;
import org.rossie.videoPlatform.Service.UserService;
import org.rossie.videoPlatform.dto.ResendTokenRequest;
import org.rossie.videoPlatform.dto.ResetPasswordDto;
import org.rossie.videoPlatform.dto.ResetPasswordRequestDto;
import org.rossie.videoPlatform.dto.UserLoginDto;
import org.rossie.videoPlatform.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/")
public class UserController {

    private final UserService userService;


    @PostMapping("v1/user/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> signUp(@RequestBody User newUser) {
        return ResponseHandler.success(userService.addNewUser(newUser), "Account Successfully Created", HttpStatus.CREATED);
    }

    @GetMapping("v1/user/verify-email")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Object> verifyEmail(@RequestParam UUID authToken) {
        return ResponseHandler.success(userService.verifyEmail(authToken), "Email Verified", HttpStatus.ACCEPTED);
    }

    @PutMapping("v1/user/newToken")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Object> newToken(@RequestBody ResendTokenRequest request) {
        return ResponseHandler.success(userService.resendVerificationToken(request.getEmail()), "Token Generated", HttpStatus.ACCEPTED);
    }

    @PostMapping("v1/user/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> login(@RequestBody UserLoginDto userLoginDto) {
        return ResponseHandler.success(userService.login(userLoginDto), "Login Successful", HttpStatus.OK);
    }

    @PostMapping("v1/user/reset-password")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Object> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto){
        return ResponseHandler.success(userService.resetPassword(resetPasswordDto), "Password Reset Successful", HttpStatus.ACCEPTED);
    }

    @PostMapping("v1/user/password-request")
    public ResponseEntity<Object> requestPasswordReset(@RequestBody ResetPasswordRequestDto resetPasswordRequestDto) {
        return ResponseHandler.success(userService.requestPasswordReset(resetPasswordRequestDto),"Password Reset Request Sent", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("v1/user/delete")
    public ResponseEntity<Object> deleteUser(@RequestBody UserLoginDto userLoginDto){
        return ResponseHandler.success(userService.deleteUser(userLoginDto),"Account Deleted", HttpStatus.OK);
    }

}