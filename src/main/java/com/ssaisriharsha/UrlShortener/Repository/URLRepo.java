package com.ssaisriharsha.UrlShortener.Repository;

import com.ssaisriharsha.UrlShortener.Entities.URLEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URLRepo extends JpaRepository<URLEntity, String> {
    URLEntity findByShortURL(String shortURL);

    URLEntity findByLongURLAndProtocol(String longURL, String Protocol);
}