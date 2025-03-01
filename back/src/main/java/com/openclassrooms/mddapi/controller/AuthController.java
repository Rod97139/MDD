package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.payload.request.UserLoginRequest;
import com.openclassrooms.mddapi.dto.payload.request.UserUpdateRequest;
import com.openclassrooms.mddapi.dto.payload.response.MessageResponse;
import com.openclassrooms.mddapi.dto.payload.response.TokenResponse;
import com.openclassrooms.mddapi.dto.payload.response.UserDisplayDto;
import com.openclassrooms.mddapi.service.JWTService;
import com.openclassrooms.mddapi.service.impl.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/me")
    public ResponseEntity<UserDisplayDto> me(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtService.getSubjectFromToken(token);
        return new ResponseEntity<>(userService.getUserDisplayByEmail(email), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<TokenResponse> update(@RequestBody UserUpdateRequest user, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtService.getSubjectFromToken(token);
        TokenResponse newToken = userService.updateUser(user, email);

        return new ResponseEntity<>(newToken, HttpStatus.OK);
    }
}
