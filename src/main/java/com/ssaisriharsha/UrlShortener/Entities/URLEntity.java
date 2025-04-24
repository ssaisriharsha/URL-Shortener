package com.ssaisriharsha.UrlShortener.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Entity
@NoArgsConstructor
@Data
@Table(name="urltable")
public class URLEntity {
    @Id
    @NotNull
    @Column(name="shorturl")
    private String shortURL;
    @Column(name="longurl")
    @NotNull
    @URL
    private String longURL;
    @Column(name="hitcount")
    @NotNull
    private int hitCount;
    @Column(name="protocol")
    private String protocol="https://";
    @ManyToOne
    @JoinColumn(name="username")
    private AppUser user;
}
