package com.farmu.urlshortener.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;

@Entity
public class UrlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String hash;

    private String url;

    @CreationTimestamp
    private Date createAt;

    public UrlEntity(){

    }

    public UrlEntity (String url, String hash) {
        this.setUrl(url);
        this.hash = hash;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
