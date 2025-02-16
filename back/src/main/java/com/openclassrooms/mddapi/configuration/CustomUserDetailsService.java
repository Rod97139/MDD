package com.openclassrooms.mddapi.configuration;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.service.impl.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto user = userService.getUserByEmail(email);
        return new User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }
}