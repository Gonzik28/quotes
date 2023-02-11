package com.gonzik.quotes.dto;

public class RequestQuoteDto {
    private String id;
    private String quote;
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }
    public String getUserId() {
        return userId;
    }
}
