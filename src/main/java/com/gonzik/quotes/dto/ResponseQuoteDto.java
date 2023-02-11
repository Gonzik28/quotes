package com.gonzik.quotes.dto;

import java.time.LocalDateTime;

public class ResponseQuoteDto {
    private String id;
    private String quote;
    private LocalDateTime localDateTime;
    private ResponseScoreDto score;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setScore(ResponseScoreDto score) {
        this.score = score;
    }

}
