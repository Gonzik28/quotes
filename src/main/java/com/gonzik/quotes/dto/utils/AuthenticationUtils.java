package com.gonzik.quotes.dto.utils;

import com.gonzik.quotes.dto.*;
import com.gonzik.quotes.entity.AuthenticationEntity;
import com.gonzik.quotes.entity.UserEntity;

public class AuthenticationUtils {
    public static ResponseAuthenticationDto authenticationEntityToDto(AuthenticationEntity authenticationEntity) {
        ResponseAuthenticationDto responseAuthenticationDto = new ResponseAuthenticationDto();
        responseAuthenticationDto.setLogin(authenticationEntity.getLogin());
        responseAuthenticationDto.setUser(UsersUtils.userEntityToDto(authenticationEntity.getUser()));
        return responseAuthenticationDto;
    }

    public static AuthenticationEntity authenticationDtoRequestToEntity(RequestAuthenticationDto requestAuthenticationDto,
                                                                        UserEntity userEntity) {
        AuthenticationEntity authenticationEntity = new AuthenticationEntity();
        authenticationEntity.setLogin(requestAuthenticationDto.getLogin());
        authenticationEntity.setPassword(requestAuthenticationDto.getPassword());
        authenticationEntity.setUser(userEntity);
        return authenticationEntity;
    }
}
