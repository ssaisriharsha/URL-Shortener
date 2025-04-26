package com.ssaisriharsha.UrlShortener.Repository;

import com.ssaisriharsha.UrlShortener.Entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, String> {}
