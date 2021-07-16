package com.lbogdanandrei.cardealerapp.service;

import com.lbogdanandrei.cardealerapp.model.UserModel;
import com.lbogdanandrei.cardealerapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserModel> user = userRepository.findUserByEmail(s);
        if(!user.isPresent())
            throw new UsernameNotFoundException("User with email " + s + " not found");
        else{
            System.out.println(user.get());
            return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), user.get().isEnabled(), true, true, true, getAuthorities("USER"));
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String authority){
        return Collections.singletonList(new SimpleGrantedAuthority(authority));
    }
}
