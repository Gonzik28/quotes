package com.gonzik.quotes.dto;

import java.time.LocalDateTime;
import java.util.Set;


public class ResponseUserDto {

    private String id;
    private String name;
    private String lastName;
    private Set<ResponseQuoteDto> quote;
    private LocalDateTime createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {return name;}
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setQuote(Set<ResponseQuoteDto> quote) {
        this.quote = quote;
    }
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

}
