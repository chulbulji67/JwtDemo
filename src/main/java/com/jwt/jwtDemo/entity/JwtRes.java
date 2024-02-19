package com.jwt.jwtDemo.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class JwtRes {
    private String jwtToken;
    private String usernaem;
}
