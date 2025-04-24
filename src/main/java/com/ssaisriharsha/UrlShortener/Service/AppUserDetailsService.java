package com.ssaisriharsha.UrlShortener.Service;

import com.ssaisriharsha.UrlShortener.Config.Security.AppUserDetails;
import com.ssaisriharsha.UrlShortener.Repository.AppUserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final AppUserRepo repo;
    public AppUserDetailsService(AppUserRepo repo) {
        this.repo=repo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) {

        return new AppUserDetails(
                repo
                .findById(username)
                .orElseThrow(
                        ()->new UsernameNotFoundException("The user with the given username doesn't exists")
                )
        );
    }
}
