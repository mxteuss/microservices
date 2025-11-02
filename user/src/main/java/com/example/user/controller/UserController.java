package com.example.user.controller;

import com.example.user.domain.User;
import com.example.user.dto.UserDTO;
import com.example.user.repositories.UserRepository;
import com.example.user.service.UserService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    @PostMapping("/users")
    public ResponseEntity<User> save(@RequestBody @Valid UserDTO dto){
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
