package org.rossie.videoPlatform.controller;

import lombok.RequiredArgsConstructor;
import org.rossie.videoPlatform.Service.UserService;
import org.rossie.videoPlatform.dto.UserDto;
import org.rossie.videoPlatform.model.User;
import org.rossie.videoPlatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("v1/user/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> signUp(@RequestBody User newUser){
        return ResponseHandler.success(userService.addNewUser(newUser),"Account Successfully Created", HttpStatus.CREATED);
    }

    @GetMapping("v1/user/verify-email")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Object> verifyEmail(@RequestParam UUID authToken){
        return ResponseHandler.success(userService.verifyEmail(authToken),"Email Verified", HttpStatus.ACCEPTED);
    }
}
