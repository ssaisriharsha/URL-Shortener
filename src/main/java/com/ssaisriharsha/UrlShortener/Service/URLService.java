package com.ssaisriharsha.UrlShortener.Service;

import com.ssaisriharsha.UrlShortener.Config.Security.AppUserDetails;
import com.ssaisriharsha.UrlShortener.DTOs.URLEntityDTO;
import com.ssaisriharsha.UrlShortener.Entities.AppUser;
import com.ssaisriharsha.UrlShortener.Entities.URLEntity;
import com.ssaisriharsha.UrlShortener.Repository.URLRepo;
import com.ssaisriharsha.UrlShortener.Utilities.DomainProtocolExtractor;
import com.ssaisriharsha.UrlShortener.Utilities.ShortURLGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        return entity.getProtocol()+"://"+entity.getLongURL();
    }
    @Transactional
    public URLEntityDTO getEntityByLongURL(AppUserDetails user, String longUrl) {
//        longUrl=urlArray.orElseThrow(()->new RuntimeException("Invalid URL"))[0];
//        String protocol=urlArray.orElseThrow(()->new RuntimeException("Invalid URL"))[1];
//        URLEntity existingEntity=repo.findByLongURLAndProtocol(longUrl, protocol);
//        URLEntity entity;
//        int hitCount=0;
//        String shortURL;
//        if(existingEntity==null) {
//            do {
//                shortURL = ShortURLGenerator.generate();
//            } while(getEntityByShortURL(shortURL)!=null);
//            entity=new URLEntity();
//            entity.setShortURL(shortURL);
//            entity.setHitCount(hitCount);
//            String[] domainProtocol= urlArray.get();
//            entity.setLongURL(domainProtocol[0]);
//            if(domainProtocol[1]!=null) entity.setProtocol(domainProtocol[1]);
//            repo.save(entity);
//        }
//        else entity=existingEntity;
//        return entity;
        Optional<String[]> urlArray= DomainProtocolExtractor.extract(longUrl);
        if(urlArray.isEmpty()) throw new RuntimeException("Invalid URL");
        String protocol=urlArray.get()[1]==null?"https":urlArray.get()[1];
        if(urlArray.get()[0].isEmpty()) throw new RuntimeException("Invalid URL");
        String domain=urlArray.get()[0];
        Optional<URLEntity> entity=repo.findURLEntityByProtocolAndLongURLAndUser_Username(protocol, domain, user.getUsername());
        if(entity.isPresent()) return new URLEntityDTO(entity.get());
        URLEntity newEntity= new URLEntity();
        String shortURL;
        do {
            shortURL=ShortURLGenerator.generate();
        } while (repo.existsById(shortURL));
        newEntity.setHitCount(0);
        newEntity.setLongURL(domain);
        newEntity.setProtocol(protocol);
        newEntity.setShortURL(shortURL);
        newEntity.setUser(user.getUser());
        repo.save(newEntity);
        return new URLEntityDTO(newEntity);
    }
    public Page<URLEntityDTO> getAllUrlsOfUser(AppUser user, int pgNo, int size, String by) {
        Pageable p= PageRequest.of(pgNo, size, Sort.Direction.DESC, by);
        return repo.findURLEntitiesByUser(user, p).map(URLEntityDTO::new);
    }
}
