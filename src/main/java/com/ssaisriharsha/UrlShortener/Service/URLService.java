package com.ssaisriharsha.UrlShortener.Service;

import com.ssaisriharsha.UrlShortener.Entities.URLEntity;
import com.ssaisriharsha.UrlShortener.Repository.URLRepo;
import com.ssaisriharsha.UrlShortener.Utilities.ProtocolDomainExtractor;
import com.ssaisriharsha.UrlShortener.Utilities.ShortURLGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return entity.getProtocol()+entity.getLongURL();
    }
    public URLEntity getEntityByLongURL(String longUrl) {
        URLEntity existingEntity=repo.findByLongURL(longUrl);
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
            String[] domainProtocol= ProtocolDomainExtractor.extract(longUrl);
            assert domainProtocol != null;
            entity.setLongURL(domainProtocol[0]);
            if(domainProtocol[1]!=null) entity.setProtocol(domainProtocol[1]);
            saveEntity(entity);
        }
        else entity=existingEntity;
        return entity;
    }
    @Transactional
    public void saveEntity(URLEntity entity) {
        repo.save(entity);
    }
    public URLEntity getEntityByShortURL(String shortUrl) {
        return repo.findByShortURL(shortUrl);
    }
}
