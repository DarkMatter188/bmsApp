package com.example.bookmyshowapp;

import com.example.bookmyshowapp.controllers.UserController;
import com.example.bookmyshowapp.dtos.*;
import com.example.bookmyshowapp.models.User;
import com.example.bookmyshowapp.services.UserService;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookMyShowAppApplicationTests {
    @Autowired
    UserController userController;
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {

    }

    @Test
    public void sampleTest(){
        SignupRequestDto requestDto = new SignupRequestDto();
        requestDto.setName("Karthick Ramesh");
        requestDto.setEmail("karthick.ramesh@gmail.com");
        requestDto.setPassword("abcde");

        SignupResponseDto responseDto = userController.signUp(requestDto);
        System.out.println(responseDto.getUserId());
    }

    @Test
    public void sampleTest1(){
        LoginRequestDto requestDto = new LoginRequestDto();
        requestDto.setEmail("apoorv.ranjan@gmail.com");
        requestDto.setPassword("abcd");

        LoginResponseDto responseDto = userController.login(requestDto);
        if(responseDto.getResponseStatus().equals(ResponseStatus.SUCCESS)){
            System.out.println("Login Successful!!");
        }
        else{
            System.out.println("Login Failed, Please try again!!");
        }
    }

}
