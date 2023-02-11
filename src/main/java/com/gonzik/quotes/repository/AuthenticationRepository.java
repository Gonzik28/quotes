package com.gonzik.quotes.repository;

import com.gonzik.quotes.entity.AuthenticationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository  extends JpaRepository<AuthenticationEntity, String> {
    AuthenticationEntity findByUserId(String id);
    AuthenticationEntity findByLoginAndPassword(String login, String passwordHash);
    AuthenticationEntity findByLogin(String login);
}
