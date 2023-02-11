package com.gonzik.quotes.controller;

import com.gonzik.quotes.dto.RequestUserDto;
import com.gonzik.quotes.dto.ResponseUserDto;
import com.gonzik.quotes.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "find/{id}")
    public ResponseEntity<ResponseUserDto> findUser(@PathVariable String id) {
        ResponseUserDto responseUserDto = userService.findById(id);
        return ResponseEntity.ok(responseUserDto);
    }

    @PostMapping(value = "create/")
    public ResponseEntity<ResponseUserDto> create(@RequestBody RequestUserDto requestUserDto) {
        ResponseUserDto responseUserDto = userService.create(requestUserDto);
        return ResponseEntity.ok(responseUserDto);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(Principal principal, @PathVariable String id) {
        String lastName = principal.getName();
        String lastNameDto = userService.findById(id).getLastName();
        if (lastName.equals(lastNameDto)) {
            userService.delete(id);
        } else {
            System.out.println("Данные для авторизации пользователя не " +
                    "могут быть удалены, пожалуйста авторризуйтесь");
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseUserDto retrievePrincipal(ResponseUserDto user) {
        return user;
    }
}
