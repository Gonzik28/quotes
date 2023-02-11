package com.gonzik.quotes.service;


import com.gonzik.quotes.dto.RequestAuthenticationDto;
import com.gonzik.quotes.dto.ResponseAuthenticationDto;
import com.gonzik.quotes.dto.utils.AuthenticationUtils;
import com.gonzik.quotes.entity.AuthenticationEntity;
import com.gonzik.quotes.entity.UserEntity;
import com.gonzik.quotes.repository.AuthenticationRepository;
import com.gonzik.quotes.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AuthenticationService {
    private final AuthenticationRepository authenticationRepository;
    private final UserRepository userRepository;

    public AuthenticationService(AuthenticationRepository authenticationRepository, UserRepository userRepository) {
        this.authenticationRepository = authenticationRepository;
        this.userRepository = userRepository;
    }

    public ResponseAuthenticationDto findById(String id) {
        AuthenticationEntity authenticationEntity = authenticationRepository.findById(id).get();
        return AuthenticationUtils.authenticationEntityToDto(authenticationEntity);
    }

    public ResponseAuthenticationDto findByUserId(String id) {
        AuthenticationEntity authenticationEntity = authenticationRepository.findByUserId(id);
        return AuthenticationUtils.authenticationEntityToDto(authenticationEntity);
    }

    public ResponseAuthenticationDto findByLoginPassword(String login, String password) {
        String passwordHash = DigestUtils.md5Hex(password);
        AuthenticationEntity authenticationEntity =
                authenticationRepository.findByLoginAndPassword(login, passwordHash);
        return AuthenticationUtils.authenticationEntityToDto(authenticationEntity);
    }

    public ResponseAuthenticationDto create(RequestAuthenticationDto requestAuthenticationDto) {
        String userId = requestAuthenticationDto.getUserId();
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        UserEntity userEntity = userEntityOptional.get();
        AuthenticationEntity authenticationEntity = AuthenticationUtils.authenticationDtoRequestToEntity(
                requestAuthenticationDto, userEntity);
        authenticationEntity.setId(UUID.randomUUID().toString());
        authenticationEntity = authenticationRepository.save(authenticationEntity);
        return AuthenticationUtils.authenticationEntityToDto(authenticationEntity);
    }

    public void delete(String id) {
        authenticationRepository.deleteById(id);
    }
}
