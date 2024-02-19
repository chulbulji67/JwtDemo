package com.jwt.jwtDemo.config;

import com.jwt.jwtDemo.entity.Role;
import com.jwt.jwtDemo.entity.User;
import com.jwt.jwtDemo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if(user == null) throw new UsernameNotFoundException("user name not found exception");
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grandedAuthurity(user.getRoles()));

    }

    private Collection<? extends GrantedAuthority> grandedAuthurity(Set<Role> roles) {
        System.out.println("Working OUtside"+roles.size());
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role: roles
             ) {

            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
            System.out.println("Working "+role);
        }
        return grantedAuthorities;
    }
}
