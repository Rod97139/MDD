package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.payload.request.UserUpdateRequest;
import com.openclassrooms.mddapi.dto.payload.response.TokenResponse;
import com.openclassrooms.mddapi.dto.payload.response.UserDisplayDto;
import com.openclassrooms.mddapi.exception.ResourceNotFoundException;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.dto.payload.request.UserLoginRequest;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.IUserService;
import com.openclassrooms.mddapi.service.JWTService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class UserService implements IUserService {


    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Instant now = Instant.now();
    private JWTService jwtService;

    @Override
    public UserDto createUser(UserDto userDto) {

        // Encode the password
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userDto.setCreatedAt(now);
        userDto.setUpdatedAt(now);

        User user = UserMapper.mapFromUserDtoToUser(userDto);
        User SavedUser = userRepository.save(user);

        return UserMapper.mapFromUserToUserDto(SavedUser);
    }

    @Override
    public UserDto getUserById(Integer UserId) {

        User user = userRepository.findById(UserId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + UserId)
        );

        return UserMapper.mapFromUserToUserDto(user);
    }

    public UserDto logUser(UserLoginRequest userLogin) {
        User user = userRepository.findByEmail(userLogin.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("User not found with email: " + userLogin.getEmail())
        );
        if (passwordEncoder.matches(userLogin.getPassword(), user.getPassword())) {
            return UserMapper.mapFromUserToUserDto(user);
        } else {
            throw new ResourceNotFoundException("Password incorrect: " + userLogin.getPassword());
        }
    }

    @Override
    public UserDto getUserByEmail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User not found with email: " + email)
        );

        return UserMapper.mapFromUserToUserDto(user);
    }

    @Override
    public UserDisplayDto getUserDisplayByEmail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User not found with email: " + email)
        );

        return UserMapper.mapFromUserToUserDisplayDto(user);
    }

    @Override
    public TokenResponse updateUser(UserUpdateRequest userUpdateData, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User not found with email: " + email)
        );
        user.setName(userUpdateData.getName());
        user.setEmail(userUpdateData.getEmail());
        user.setUpdatedAt(now);
        userRepository.save(user);

        return jwtService.generateToken(new UserLoginRequest(user.getEmail(), user.getPassword()));
    }
}
