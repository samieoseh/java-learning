package com.test.auth.services;

import com.test.auth.models.UserPrincipal;
import com.test.auth.repo.UserRepo;
import com.test.auth.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username: " + username);
        Users user = repo.findByUsername(username);

        System.out.println("user: " + user);

        if (user == null)
            System.out.println("User not found");

        return new UserPrincipal(user);
    }
}
