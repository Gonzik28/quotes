package com.gonzik.quotes.service;

import com.gonzik.quotes.entity.AuthenticationEntity;
import com.gonzik.quotes.entity.UserEntity;
import com.gonzik.quotes.repository.AuthenticationRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.hibernate.cfg.AvailableSettings.USER;
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    private final AuthenticationRepository authenticationRepository;

    public CustomUserDetailsService(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        AuthenticationEntity authenticationEntity = authenticationRepository.findByLogin(login);
        UserEntity myUser = authenticationEntity.getUser();
        if (myUser == null) {
            throw new UsernameNotFoundException("Unknown user: " + myUser.getName());
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(authenticationEntity.getPassword());
        UserDetails user = User.builder()
                .username(authenticationEntity.getLogin())
                .password(encodedPassword)
                .roles(USER)
                .build();
        return user;
    }
}
