package com.gonzik.quotes.dto.utils;

import com.gonzik.quotes.dto.RequestUserDto;
import com.gonzik.quotes.dto.ResponseUserDto;
import com.gonzik.quotes.entity.QuoteEntity;
import com.gonzik.quotes.entity.UserEntity;

import java.util.Set;

public class UsersUtils {
    public static ResponseUserDto userEntityToDto(UserEntity userEntity) {
        ResponseUserDto responseUserDto = new ResponseUserDto();
        responseUserDto.setId(userEntity.getId());
        responseUserDto.setName(userEntity.getName());
        responseUserDto.setLastName(userEntity.getLastName());
        responseUserDto.setCreateDate(userEntity.getCreateDate());
        responseUserDto.setQuote(QuoteUtils.quoteEntitiesToDto(userEntity.getQuoteEntities()));
        return responseUserDto;
    }
    public static UserEntity userDtoRequestToEntity(RequestUserDto requestUserDto,Set<QuoteEntity> quoteEntities) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(requestUserDto.getName());
        userEntity.setLastName(requestUserDto.getLastName());
        userEntity.setQuoteEntities(quoteEntities);
        return userEntity;
    }
}
