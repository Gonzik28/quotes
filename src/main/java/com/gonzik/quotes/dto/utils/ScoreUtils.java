package com.gonzik.quotes.dto.utils;

import com.gonzik.quotes.dto.RequestScoreDto;
import com.gonzik.quotes.dto.ResponseScoreDto;
import com.gonzik.quotes.entity.QuoteEntity;
import com.gonzik.quotes.entity.ScoreEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class ScoreUtils {
    public static ResponseScoreDto scoreEntityToDto(ScoreEntity scoreEntity) {
        ResponseScoreDto responseScoreDto = new ResponseScoreDto();
        responseScoreDto.setId(scoreEntity.getId());
        responseScoreDto.setScoresNegative(scoreEntity.getScoresNegative());
        responseScoreDto.setScoresPositive(scoreEntity.getScoresPositive());
        responseScoreDto.setDate(scoreEntity.getUpdateDate());
        return responseScoreDto;
    }

    public static Set<ResponseScoreDto> scoreEntitiesToDto(Set<ScoreEntity> userEntity) {
        return userEntity.stream()
                .map(ScoreUtils :: scoreEntityToDto)
                .collect(Collectors.toSet());
    }
    public static ScoreEntity scoreDtoRequestToEntity(RequestScoreDto requestScoreDto, QuoteEntity quoteEntity) {
        ScoreEntity scoreEntity = new ScoreEntity();
        scoreEntity.setId(requestScoreDto.getId());
        scoreEntity.setQuote(quoteEntity);
        scoreEntity.setScoresNegative(requestScoreDto.getScoresNegative());
        scoreEntity.setScoresPositive(requestScoreDto.getScoresPositive());
        return scoreEntity;
    }
}
