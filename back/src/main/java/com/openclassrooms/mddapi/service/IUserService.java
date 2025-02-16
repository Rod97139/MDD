package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.UserDto;

public interface IUserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Integer UserId);

    UserDto getUserByEmail(String email);
}
