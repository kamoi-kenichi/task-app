package com.kenichikamoi.taskapp.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kenichikamoi.taskapp.entity.SiteUser;
import com.kenichikamoi.taskapp.repository.SiteUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SiteUserRepository siteUserRepository;

    public UserDetailsServiceImpl(SiteUserRepository siteUserRepository) {
        this.siteUserRepository = siteUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SiteUser siteUser = siteUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません: " + username));

        return User.withUsername(siteUser.getUsername())
                .password(siteUser.getPassword())
                .roles("USER")
                .build();
    }
}