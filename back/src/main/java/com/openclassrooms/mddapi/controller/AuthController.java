package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.payload.request.UserLoginRequest;
import com.openclassrooms.mddapi.payload.response.TokenResponse;
import com.openclassrooms.mddapi.service.JWTService;
import com.openclassrooms.mddapi.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private UserService userService;
    private JWTService jwtService;

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.createUser(userDto);
        UserLoginRequest userLoginRequest = new UserLoginRequest(savedUser.getEmail(), savedUser.getPassword());
        return new ResponseEntity<>(jwtService.generateToken(userLoginRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody UserLoginRequest userLoginRequest) {

       if (userService.logUser(userLoginRequest) == null) {
           return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
         }

        return new ResponseEntity<>(jwtService.generateToken(userLoginRequest), HttpStatus.OK);
    }
}
