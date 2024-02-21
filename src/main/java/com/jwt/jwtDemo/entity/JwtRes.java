package com.jwt.jwtDemo.entity;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class JwtRes {
    private String jwtToken;
    private String username;
    private List<String> roles;
}
