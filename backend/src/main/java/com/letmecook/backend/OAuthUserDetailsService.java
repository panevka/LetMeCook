package com.letmecook.backend;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class OAuthUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password("")
                .authorities("ROLE_USER")
                .build();
    }
}
