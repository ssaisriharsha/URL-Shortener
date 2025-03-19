package com.ssaisriharsha.UrlShortener.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Table(name="urltable")
public class URLEntity {
    @Id
    @NonNull
    @Column(name="shorturl")
    private String shortURL;

    @Column(name="longurl")
    @NonNull
    private String longURL;

    @Column(name="hitcount")
    @NonNull
    private int hitCount;

    @Column(name="protocol")
    private String protocol;


}
