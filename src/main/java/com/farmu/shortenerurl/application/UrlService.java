package com.farmu.shortenerurl.application;

import com.farmu.shortenerurl.infraestructure.UrlDTO;
import com.google.common.hash.Hashing;
import com.farmu.shortenerurl.infraestructure.UrlRepository;
import com.farmu.shortenerurl.domain.UrlEntity;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

@Service
public class UrlService {

    private UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String getUrl(String shortUrl) {
        UrlEntity ue = this.urlRepository.findByHash(shortUrl);
        if (ue != null)
            return ue.getUrl();
        return "";
    }

    public UrlDTO createShortUrl(String url) throws MalformedURLException {
        UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
        UrlDTO urlDTO = new UrlDTO();

        if (urlValidator.isValid(url)) {
            urlDTO.setShortUrl(Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString());
            urlDTO.setUrl(url);
            UrlEntity urlEntity = new UrlEntity(url, urlDTO.getShortUrl());
            if (!existUrl(url))
                this.urlRepository.save(urlEntity);
        }
        else
            throw new MalformedURLException();
        return urlDTO;
    }

    private boolean existUrl(String url) {
        return (this.urlRepository.findByUrl(url) != null);
    }
}