package com.gonzik.quotes.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quotes")
public class QuoteEntity implements Comparable<Object> {
    @Id
    private String id;
    private String quote;

    @CreationTimestamp
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name="id_user", nullable=false)
    private UserEntity user;

    @OneToOne(mappedBy = "quote")
    private ScoreEntity score;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ScoreEntity getScore() {
        return score;
    }

    public void setScore(ScoreEntity score) {
        this.score = score;
    }

    @Override
    public int compareTo(Object o) {
        QuoteEntity f = (QuoteEntity) o;
        return this.getDate().compareTo(f.getDate());
    }

}
