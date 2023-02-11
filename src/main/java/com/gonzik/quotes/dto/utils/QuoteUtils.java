package com.gonzik.quotes.dto.utils;

import com.gonzik.quotes.dto.ResponseQuoteDto;
import com.gonzik.quotes.entity.QuoteEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class QuoteUtils {
    public static ResponseQuoteDto quoteEntityToDto(QuoteEntity quoteEntity) {
        ResponseQuoteDto responseQuoteDto = new ResponseQuoteDto();
        responseQuoteDto.setId(quoteEntity.getId());
        responseQuoteDto.setQuote(quoteEntity.getQuote());
        responseQuoteDto.setLocalDateTime(quoteEntity.getDate());
        if(quoteEntity.getScore()!=null){
            responseQuoteDto.setScore(ScoreUtils.scoreEntityToDto(quoteEntity.getScore()));
        }
        return responseQuoteDto;
    }

    public static Set<ResponseQuoteDto> quoteEntitiesToDto(Set<QuoteEntity> quoteEntities) {
            return quoteEntities.stream()
                    .map(QuoteUtils :: quoteEntityToDto)
                    .collect(Collectors.toSet());
    }

}
