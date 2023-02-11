package com.gonzik.quotes.dto;


public class RequestScoreDto {
    private String id;
    private Long scoresPositive;
    private Long scoresNegative;
    private String quoteId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getScoresPositive() {
        return scoresPositive;
    }
    public Long getScoresNegative() {
        return scoresNegative;
    }

    public String getQuoteId() {
        return quoteId;
    }

}
