package com.ssaisriharsha.UrlShortener.Repository;

import com.ssaisriharsha.UrlShortener.DTOs.URLEntityDTO;
import com.ssaisriharsha.UrlShortener.Entities.AppUser;
import com.ssaisriharsha.UrlShortener.Entities.URLEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface URLRepo extends JpaRepository<URLEntity, String> {
    URLEntity findByShortURL(String shortURL);

    URLEntity findByLongURLAndProtocol(String longURL, String Protocol);

    Optional<URLEntity> findURLEntityByProtocolAndLongURLAndUser_Username(String protocol, String longURL, String userUsername);

    Page<URLEntity> findURLEntitiesByUser(AppUser user, Pageable pageable);
}