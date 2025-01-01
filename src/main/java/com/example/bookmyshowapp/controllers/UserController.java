package com.example.bookmyshowapp.controllers;

import com.example.bookmyshowapp.dtos.*;
import com.example.bookmyshowapp.exceptions.UserNotFoundException;
import com.example.bookmyshowapp.models.User;
import com.example.bookmyshowapp.repositories.UserRepository;
import com.example.bookmyshowapp.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserRepository userRepository;
    private UserService userService;

    public UserController(UserService userService, UserRepository userRepository){
        this.userService = userService;
        this.userRepository = userRepository;
    }
    public SignupResponseDto signUp(SignupRequestDto signupRequestDto){
        SignupResponseDto responseDto = new SignupResponseDto();
        try {
            User user = userService.signUp(signupRequestDto.getName(), signupRequestDto.getEmail(), signupRequestDto.getPassword());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setUserId(user.getId());
        } catch (UserNotFoundException e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            throw new RuntimeException(e);
        }
        return responseDto;
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto){
        LoginResponseDto responseDto = new LoginResponseDto();
        try {
            User user = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
//            throw new RuntimeException(e);
        }

        return responseDto;
    }
}
