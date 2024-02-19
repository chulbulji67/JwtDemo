package com.jwt.jwtDemo.controller;

import com.jwt.jwtDemo.entity.JwtReq;
import com.jwt.jwtDemo.entity.JwtRes;
import com.jwt.jwtDemo.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

   @Autowired
    AuthService authService;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtRes> login(@RequestBody JwtReq request) {

        return ResponseEntity.status(HttpStatus.OK).body( authService.authService(request));
    }





}
