package com.jwt.jwtDemo.service;

import com.jwt.jwtDemo.config.UserDetailsServiceImpl;
import com.jwt.jwtDemo.entity.JwtReq;
import com.jwt.jwtDemo.entity.JwtRes;
import com.jwt.jwtDemo.entity.Role;
import com.jwt.jwtDemo.jwt.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

    public JwtRes authService(JwtReq request){

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        String token = this.helper.generateToken(userDetails);

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        JwtRes jwtRes = new JwtRes();

        List<String> roles = new ArrayList<>();
        for (GrantedAuthority authority: authorities
             ) {
//            System.out.println(authority.getAuthority());
            roles.add(authority.getAuthority());

        }


         jwtRes = JwtRes.builder()
                .jwtToken(token)
                .username(userDetails.getUsername())
                 .roles(roles).build();
        return jwtRes;
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
