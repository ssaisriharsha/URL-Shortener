package com.ssaisriharsha.UrlShortener.Controller;

import com.ssaisriharsha.UrlShortener.Config.Security.AppUserDetails;
import com.ssaisriharsha.UrlShortener.DTOs.AppUserDTO;
import com.ssaisriharsha.UrlShortener.DTOs.URLEntityDTO;
import com.ssaisriharsha.UrlShortener.Entities.AppUser;
import com.ssaisriharsha.UrlShortener.Entities.URLEntity;
import com.ssaisriharsha.UrlShortener.Service.AppUserDetailsService;
import com.ssaisriharsha.UrlShortener.Service.URLService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api")
public class URLRestController {
    private final URLService service;

    public URLRestController(URLService service) {
        this.service=service;
    }
    @GetMapping("/profile")
    public ResponseEntity<AppUserDTO> getProfile(@AuthenticationPrincipal AppUserDetails appUserDetails) {
        return new ResponseEntity<>(new AppUserDTO(appUserDetails.getUser()), HttpStatus.OK);
    }
    @GetMapping("/redirect/{id}")
    public void redirectToLongURL(@PathVariable("id") String shortURL, HttpServletResponse response) throws IOException {
        response.sendRedirect(service.getLongUrl(shortURL));
    }
    @GetMapping("/myurls")
    public ResponseEntity<Page<URLEntityDTO>> fetchUrlsByUser(@AuthenticationPrincipal AppUserDetails appUserDetails, @RequestParam(name="pgNo", defaultValue="0") int pgNo, @RequestParam(name="size", defaultValue="10") int size, @RequestParam(name="sortBy", defaultValue="createdOn") String by) {
        return new ResponseEntity<>(service.getAllUrlsOfUser(appUserDetails.getUser(), pgNo, size, by), HttpStatus.OK);
    }
    @PostMapping("/shorten")
    public ResponseEntity<URLEntityDTO> createOrRetrieveShortUrl(@RequestBody URLEntityDTO entity, @AuthenticationPrincipal AppUserDetails userDetails) {
        if(entity.getShortURL()!=null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        URLEntityDTO createdEntity=service.getEntityByLongURL(userDetails, entity.getLongURL());
        return new ResponseEntity<>(createdEntity, HttpStatus.OK);
    }
}
