package com.example.bookmyshowapp.repositories;

import com.example.bookmyshowapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    // select * from users where id = userId;
    @Override
    Optional<User> findById(Long userId);


    Optional<User> findByEmail(String email);


}
