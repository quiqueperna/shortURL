package com.farmu.shortenerurl.infraestructure;

import com.farmu.shortenerurl.domain.UrlEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends CrudRepository<UrlEntity, Long> {

    UrlEntity save(UrlEntity urlEntity);

    UrlEntity findByUrl(String url);

    UrlEntity findByHash(String shortUrl);
}
