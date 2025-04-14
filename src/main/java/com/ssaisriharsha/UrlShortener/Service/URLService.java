package com.ssaisriharsha.UrlShortener.Service;

import com.ssaisriharsha.UrlShortener.Entities.URLEntity;
import com.ssaisriharsha.UrlShortener.Repository.URLRepo;
import com.ssaisriharsha.UrlShortener.Utilities.ProtocolDomainExtractor;
import com.ssaisriharsha.UrlShortener.Utilities.ShortURLGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class URLService {
    private final URLRepo repo;
    public URLService(URLRepo repo) {
        this.repo=repo;
    }
    @Transactional
    public String getLongUrl(String shortUrl) {
        URLEntity entity=repo.findByShortURL(shortUrl);
        entity.setHitCount(entity.getHitCount()+1);
        if(entity.getHitCount()%10==0) return "https://www.google.com/";
        return entity.getProtocol()+entity.getLongURL();
    }
    @Transactional
    public URLEntity getEntityByLongURL(String longUrl) {
        Optional<String[]> urlArray=ProtocolDomainExtractor.extract(longUrl);
        longUrl=urlArray.orElseThrow(()->new RuntimeException("Invalid URL"))[0];
        String protocol=urlArray.orElseThrow(()->new RuntimeException("Invalid URL"))[1];
        URLEntity existingEntity=repo.findByLongURLAndProtocol(longUrl, protocol);
        URLEntity entity;
        int hitCount=0;
        String shortURL;
        if(existingEntity==null) {
            do {
                shortURL = ShortURLGenerator.generate();
            } while(getEntityByShortURL(shortURL)!=null);
            entity=new URLEntity();
            entity.setShortURL(shortURL);
            entity.setHitCount(hitCount);
            String[] domainProtocol= urlArray.get();
            entity.setLongURL(domainProtocol[0]);
            if(domainProtocol[1]!=null) entity.setProtocol(domainProtocol[1]);
            repo.save(entity);
        }
        else entity=existingEntity;
        return entity;
    }
    public URLEntity getEntityByShortURL(String shortUrl) {
        return repo.findByShortURL(shortUrl);
    }
}
