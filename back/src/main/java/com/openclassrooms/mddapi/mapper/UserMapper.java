package com.openclassrooms.mddapi.mapper;


import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.payload.response.UserDisplayDto;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.utils.DateUtils;

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

    public static UserDisplayDto mapFromUserToUserDisplayDto(User user) {
        return new UserDisplayDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                DateUtils.formatInstant(user.getCreatedAt()),
                DateUtils.formatInstant(user.getUpdatedAt())
        );
    }
}