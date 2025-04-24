package com.ssaisriharsha.UrlShortener.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="users")
@NoArgsConstructor
@Data
public class AppUser {
    @Id
    @Column(name="username")
    private String username;
    @Column(name="password")
    @Size(min=8)
    private String password;
    @Column(name="createdOn")
    private final LocalDateTime createdOn=LocalDateTime.now();
    @Column(name="email")
    @NotNull
    @Email
    private String email;
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<URLEntity> UrlEntities;
}
