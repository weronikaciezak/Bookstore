package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
    //to ma uzywac UserRepository
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
