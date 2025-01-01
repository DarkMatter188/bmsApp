package com.example.bookmyshowapp.services;

import com.example.bookmyshowapp.exceptions.UserNotFoundException;
import com.example.bookmyshowapp.models.User;
import com.example.bookmyshowapp.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUp(String name, String email, String password) throws UserNotFoundException {

        /*
        1. Check if user already exists with given email id.
        2. If yes, ask User to login
        3. If not create a new User obj with details and save to DB
         */

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(!optionalUser.isEmpty()){
            throw new UserNotFoundException("User with email: "+ email +" already exists");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password)); // Should not store pwd as it is

        return userRepository.save(user);
    }

    public User login(String email, String password) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User with email: "+ email + " Not Found!");
        }
        User user = optionalUser.get();
        //Compare for password if it is correct
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if(bCryptPasswordEncoder.matches(password, user.getPassword())){
            //Login success
            return user;
        }

        throw new RuntimeException("Password is incorrect!!");
    }
}
