package com.neighbor.userservice.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@ComponentScan("com.neighbor.userservice")
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //use what stores your user to retrieve
        com.neighbor.userservice.entity.User myUser = userService.getByUsername(username);

        if(myUser == null) {
            throw new UsernameNotFoundException("User with name: "+ username +" not found");
        }
        return new User(myUser.getUsername(),myUser.getPassword(), new ArrayList<GrantedAuthority>());
    }

}
