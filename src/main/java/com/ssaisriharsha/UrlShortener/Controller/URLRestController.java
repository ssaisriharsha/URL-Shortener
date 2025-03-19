package com.ssaisriharsha.UrlShortener.Controller;

import com.ssaisriharsha.UrlShortener.Entities.URLEntity;
import com.ssaisriharsha.UrlShortener.Service.URLService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api")
public class URLRestController {
    private final URLService service;
    public URLRestController(URLService service) {
        this.service=service;
    }
    @GetMapping("/{id}")
    public void redirectToLongURL(@PathVariable("id") String shortURL, HttpServletResponse response) throws IOException {
        response.sendRedirect(service.getLongUrl(shortURL));
    }
    @PostMapping("/shorten")
    public ResponseEntity<URLEntity> createOrRetrieveShortUrl(@RequestBody URLEntity entity) {
        if(entity.getShortURL()!=null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        entity=service.getEntityByLongURL(entity.getLongURL());
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
}
