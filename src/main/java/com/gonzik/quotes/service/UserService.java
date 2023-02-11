package com.gonzik.quotes.service;

import com.gonzik.quotes.dto.RequestUserDto;
import com.gonzik.quotes.dto.ResponseUserDto;
import com.gonzik.quotes.dto.utils.UsersUtils;
import com.gonzik.quotes.entity.QuoteEntity;
import com.gonzik.quotes.entity.UserEntity;
import com.gonzik.quotes.repository.AuthenticationRepository;
import com.gonzik.quotes.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;


@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationRepository authenticationRepository;

    public UserService(UserRepository userRepository, AuthenticationRepository authenticationRepository) {
        this.userRepository = userRepository;
        this.authenticationRepository = authenticationRepository;
    }

    public ResponseUserDto findById(String id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new NoSuchElementException("Пользователя с указанным id = " + id + " не существует");
        } else {
            UserEntity userEntity = userRepository.findById(id).get();
            return UsersUtils.userEntityToDto(userEntity);
        }
    }

    public ResponseUserDto create(RequestUserDto requestUserDto) {
        if (userRepository.findById(requestUserDto.getId()).isPresent()) {
            throw new NoSuchElementException("Пользователь с указанным id = " + requestUserDto.getId()
                    + " уже существует");
        } else {
            Set<QuoteEntity> quoteEntities = new HashSet<>();
            UserEntity userEntity = UsersUtils.userDtoRequestToEntity(requestUserDto,quoteEntities);
            userEntity.setId(UUID.randomUUID().toString());
            userEntity = userRepository.save(userEntity);
            return UsersUtils.userEntityToDto(userEntity);
        }
    }

    public void delete(String id) {
        String authorizationId = authenticationRepository.findByUserId(id).getId();
        authenticationRepository.deleteById(authorizationId);
        userRepository.deleteById(id);
    }

    public ResponseUserDto findByQuoteId(String id) {
        if (!userRepository.findByQuoteId(id).isPresent()) {
            throw new NoSuchElementException("Пользователя с указанным id = " + id + " не существует");
        } else {
            UserEntity userEntity = userRepository.findByQuoteId(id).get();
            return UsersUtils.userEntityToDto(userEntity);
        }

    }
}
