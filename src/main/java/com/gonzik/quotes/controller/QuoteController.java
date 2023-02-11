package com.gonzik.quotes.controller;

import com.gonzik.quotes.dto.RequestQuoteDto;
import com.gonzik.quotes.dto.RequestScoreDto;
import com.gonzik.quotes.dto.ResponseQuoteDto;
import com.gonzik.quotes.dto.ResponseScoreDto;
import com.gonzik.quotes.service.QuoteService;
import com.gonzik.quotes.service.ScoreService;
import com.gonzik.quotes.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/quote/")
public class QuoteController {

    private final QuoteService quoteService;
    private final UserService userService;
    private final ScoreService scoreService;

    public QuoteController(QuoteService quoteService, UserService userService, ScoreService scoreService) {
        this.quoteService = quoteService;
        this.userService = userService;
        this.scoreService = scoreService;
    }

    @GetMapping(value = "find/{id}")
    public ResponseEntity<ResponseQuoteDto> findScore(@PathVariable String id) {
        ResponseQuoteDto responseQuoteDto = quoteService.findById(id);
        return ResponseEntity.ok(responseQuoteDto);
    }

    @GetMapping(value = "last/{last}")
    public ResponseEntity<Set<ResponseQuoteDto>> lastQuote(@PathVariable String last) {
        int number = Integer.parseInt(last);
        Set<ResponseQuoteDto> responseQuoteDto = quoteService.last(number);
        return ResponseEntity.ok(responseQuoteDto);
    }

    @GetMapping(value = "random/")
    public ResponseEntity<ResponseQuoteDto> random() {
        ResponseQuoteDto responseQuoteDto = quoteService.random();
        return ResponseEntity.ok(responseQuoteDto);
    }

    @PostMapping(value = "create/")
    public ResponseEntity<ResponseQuoteDto> create(@RequestBody RequestQuoteDto requestQuoteDto) {
        RequestScoreDto requestScoreDto = new RequestScoreDto();
        ResponseScoreDto responseScoreDto = scoreService.create(requestScoreDto);
        ResponseQuoteDto responseQuoteDto = quoteService.create(requestQuoteDto);
        responseQuoteDto.setScore(responseScoreDto);
        return ResponseEntity.ok(responseQuoteDto);
    }

    @PostMapping(value = "update/")
    public ResponseEntity<ResponseQuoteDto> create(Principal principal, @RequestBody RequestQuoteDto requestQuoteDto) {
        String lastName = principal.getName();
        String lastNameDto = userService.findByQuoteId(requestQuoteDto.getId()).getLastName();
        if (lastName.equals(lastNameDto)) {
            ResponseQuoteDto responseQuoteDto = quoteService.update(requestQuoteDto);
            return ResponseEntity.ok(responseQuoteDto);
        }else{
            System.out.println("Данные для авторизации пользователя не " +
                    "могут быть удалены, пожалуйста авторризуйтесь");
            return ResponseEntity.noContent().build();

        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(Principal principal, @PathVariable String id) {
        String lastName = principal.getName();
        String lastNameDto = userService.findByQuoteId(id).getLastName();
        if (lastName.equals(lastNameDto)) {
            quoteService.delete(id);
        } else {
            System.out.println("Данные для авторизации пользователя не " +
                    "могут быть удалены, пожалуйста авторризуйтесь");
        }
        return ResponseEntity.noContent().build();
    }
}
