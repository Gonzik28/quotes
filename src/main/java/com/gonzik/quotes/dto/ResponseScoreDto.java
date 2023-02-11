package com.gonzik.quotes.dto;

import java.time.LocalDateTime;

public class ResponseScoreDto {

    private String id;
    private long scoresPositive;
    private long scoresNegative;
    private LocalDateTime date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setScoresPositive(long scoresPositive) {
        this.scoresPositive = scoresPositive;
    }
    public void setScoresNegative(long scoresNegative) {
        this.scoresNegative = scoresNegative;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
