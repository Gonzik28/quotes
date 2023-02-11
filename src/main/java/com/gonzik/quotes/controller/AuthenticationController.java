package com.gonzik.quotes.controller;

import com.gonzik.quotes.dto.RequestAuthenticationDto;
import com.gonzik.quotes.dto.ResponseAuthenticationDto;
import com.gonzik.quotes.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/aut/")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping(value = "find/{id}")
    public ResponseEntity<ResponseAuthenticationDto> findById(@PathVariable String id) {
        ResponseAuthenticationDto authorizationDto = authenticationService.findById(id);
        return ResponseEntity.ok(authorizationDto);
    }

    @GetMapping(value = "logPass/{login}&{password}")
    public ResponseEntity<ResponseAuthenticationDto> findByLoginPassword(
            @PathVariable String login,
            @PathVariable String password) {
        ResponseAuthenticationDto authorizationDto = authenticationService.findByLoginPassword(login, password);
        return ResponseEntity.ok(authorizationDto);
    }

    @PostMapping(value = "create/")
    public ResponseEntity<ResponseAuthenticationDto> create(
            @RequestBody RequestAuthenticationDto requestAuthenticationDto) {
        ResponseAuthenticationDto authenticationDto = authenticationService.create(requestAuthenticationDto);
        return ResponseEntity.ok(authenticationDto);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(Principal principal, @PathVariable String id) {
        String name = principal.getName();
        String nameDto = authenticationService.findByUserId(id).getUser().getLastName();
        if (name.equals(nameDto)) {
            authenticationService.delete(id);
        } else {
            System.out.println("Данные для авторизации пользователя не " +
                    "могут быть удалены, пожалуйста авторризуйтесь");
        }
        return ResponseEntity.noContent().build();
    }
}
