package com.gonzik.quotes.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private String id;
    private String name;
    private String lastName;
    @CreationTimestamp
    private LocalDateTime createDate;
    @OneToMany(mappedBy="user")
    private Set<QuoteEntity> quote;
    @OneToOne(mappedBy="user")
    private AuthenticationEntity authentication;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Set<QuoteEntity> getQuoteEntities() {
        return quote;
    }

    public void setQuoteEntities(Set<QuoteEntity> quote) {
        this.quote = quote;
    }

    public AuthenticationEntity getAuthentication() {
        return authentication;
    }

    public void setAuthentication(AuthenticationEntity authentication) {
        this.authentication = authentication;
    }

}
