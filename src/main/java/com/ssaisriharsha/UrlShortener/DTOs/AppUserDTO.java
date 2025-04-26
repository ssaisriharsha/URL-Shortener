package com.ssaisriharsha.UrlShortener.DTOs;

import com.ssaisriharsha.UrlShortener.Entities.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
public class AppUserDTO {
    private String username;
    private String email;
    private LocalDateTime createdOn;

    public AppUserDTO(AppUser user) {
        this.username=user.getUsername();
        this.email=user.getEmail();
        this.createdOn=user.getCreatedOn();
    }
}
