package com.jwt.jwtDemo.controller;

import com.jwt.jwtDemo.entity.User;
import com.jwt.jwtDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> regiserUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(user));
    }
    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }

}
