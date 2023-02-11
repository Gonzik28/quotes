package com.gonzik.quotes.controller;

import com.gonzik.quotes.dto.RequestScoreDto;
import com.gonzik.quotes.dto.ResponseScoreDto;
import com.gonzik.quotes.service.ScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/score/")
public class ScoreController {
    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping(value = "topOrFlop/{county}&{top}")
    public ResponseEntity<Set<ResponseScoreDto>> topOrFlop(
            @PathVariable String county,
            @PathVariable Boolean top) {
        int number = Integer.parseInt(county);
        Set<ResponseScoreDto> responseScoreDto = scoreService.topOrFlop(number,top);
        return ResponseEntity.ok(responseScoreDto);
    }

    @GetMapping(value = "update/{id}&{positive}&{negative}")
    public ResponseEntity<ResponseScoreDto> update(@PathVariable String id,
                                                   @PathVariable Boolean positive,
                                                   @PathVariable Boolean negative) {
        ResponseScoreDto responseScoreDto = scoreService.update(id,positive,negative);
        return ResponseEntity.ok(responseScoreDto);
    }

    @PostMapping(value = "create/")
    public ResponseEntity<ResponseScoreDto> create(@RequestBody RequestScoreDto requestScoreDto) {
        ResponseScoreDto responseScoreDto = scoreService.create(requestScoreDto);
        return ResponseEntity.ok(responseScoreDto);
    }

    @PostMapping(value = "update/")
    public ResponseEntity<ResponseScoreDto> update(@RequestBody RequestScoreDto requestScoreDto) {
        ResponseScoreDto responseScoreDto = scoreService.update(requestScoreDto);
        return ResponseEntity.ok(responseScoreDto);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        scoreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
