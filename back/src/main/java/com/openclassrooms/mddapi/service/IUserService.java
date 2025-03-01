package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.payload.request.UserUpdateRequest;
import com.openclassrooms.mddapi.dto.payload.response.TokenResponse;
import com.openclassrooms.mddapi.dto.payload.response.UserDisplayDto;

public interface IUserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Integer UserId);

    UserDto getUserByEmail(String email);

    UserDisplayDto getUserDisplayByEmail(String email);

    TokenResponse updateUser(UserUpdateRequest userUpdateData, String email);
}
