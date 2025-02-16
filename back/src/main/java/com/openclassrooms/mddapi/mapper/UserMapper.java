package com.openclassrooms.mddapi.mapper;


import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.model.User;

public class UserMapper {
    public static UserDto mapFromUserToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPassword(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public static User mapFromUserDtoToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getEmail(),
                userDto.getName(),
                userDto.getPassword(),
                userDto.getCreatedAt(),
                userDto.getUpdatedAt()
        );
    }
}