package com.ssaisriharsha.UrlShortener.DTOs;

import com.ssaisriharsha.UrlShortener.Entities.URLEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class URLEntityDTO {
    private String shortURL;
    private String longURL;
    private LocalDateTime createdOn;
    public URLEntityDTO(URLEntity entity) {
        this.longURL= entity.getProtocol()+"://"+entity.getLongURL();
        this.shortURL= entity.getShortURL();
        this.createdOn=entity.getCreatedOn();
    }
}
