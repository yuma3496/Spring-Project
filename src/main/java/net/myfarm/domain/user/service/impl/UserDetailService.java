package net.myfarm.domain.user.service.impl;

import net.myfarm.domain.user.model.MUser;
import net.myfarm.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserService service;

    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {

        // Get user info
        MUser loginUser = service.getLoginUser(username);

        // If not exists
        if(loginUser == null)
            throw new UsernameNotFoundException("user not found");


    // Generate authorization list
    GrantedAuthority authority = new SimpleGrantedAuthority(loginUser.getRole());
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(authority);

    //Generate UserDetails
    UserDetails userDetails = (UserDetails) new User(loginUser.getUserId()),
    loginUser.getPassword(),
    authorities);

    return userDetails;
    }
}
