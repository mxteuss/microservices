package com.example.user.service;

import com.example.user.domain.User;
import com.example.user.producer.UserProducer;
import com.example.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional
    public User save(User user){
        user = userRepository.save(user);
        userProducer.publishMessageEmail(user);
        return user;
    }
}
