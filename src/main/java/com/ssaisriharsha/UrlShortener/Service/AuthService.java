package com.ssaisriharsha.UrlShortener.Service;

import com.ssaisriharsha.UrlShortener.Entities.AppUser;
import com.ssaisriharsha.UrlShortener.Exceptions.UserExistsException;
import com.ssaisriharsha.UrlShortener.Repository.AppUserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AppUserRepo repo;
    private final PasswordEncoder encoder;
    public AuthService(AppUserRepo repo, PasswordEncoder encoder) {
        this.encoder=encoder;
        this.repo=repo;
    }
    public void createUser(AppUser user) {
        if(repo.existsById(user.getUsername())) throw new UserExistsException("The user with the username " + user.getUsername() + " already exists");
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
    }
}
