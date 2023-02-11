package com.gonzik.quotes.entity;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "scores")
public class ScoreEntity implements Comparable<Object> {
    @Id
    private String id;
    private Long scoresPositive;
    private Long scoresNegative;
    @UpdateTimestamp
    private LocalDateTime updateDate;
    @OneToOne
    @JoinColumn(name = "id_quotes", referencedColumnName = "id")
    private QuoteEntity quote;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getScoresPositive() {
        return scoresPositive;
    }

    public void setScoresPositive(Long scoresPositive) {
        this.scoresPositive = scoresPositive;
    }

    public Long getScoresNegative() {
        return scoresNegative;
    }

    public void setScoresNegative(Long scoresNegative) {
        this.scoresNegative = scoresNegative;
    }

    public QuoteEntity getQuote() {
        return quote;
    }

    public void setQuote(QuoteEntity quote) {
        this.quote = quote;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public int compareTo(Object o) {
        ScoreEntity f = (ScoreEntity) o;
        Long a = this.getScoresPositive() - this.getScoresNegative();
        Long b = f.getScoresPositive() - f.getScoresNegative();
        return a.compareTo(b);
    }

}
