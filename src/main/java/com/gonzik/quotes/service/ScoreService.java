package com.gonzik.quotes.service;

import com.gonzik.quotes.dto.RequestScoreDto;
import com.gonzik.quotes.dto.ResponseScoreDto;
import com.gonzik.quotes.dto.utils.ScoreUtils;
import com.gonzik.quotes.entity.QuoteEntity;
import com.gonzik.quotes.entity.ScoreEntity;
import com.gonzik.quotes.repository.QuoteRepository;
import com.gonzik.quotes.repository.ScoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.Collections.reverseOrder;

@Service
@Transactional
public class ScoreService {
    private final QuoteRepository quoteRepository;
    private final ScoreRepository scoreRepository;


    public ScoreService(QuoteRepository quoteRepository, ScoreRepository scoreRepository) {
        this.quoteRepository = quoteRepository;
        this.scoreRepository = scoreRepository;
    }


    public ResponseScoreDto create(RequestScoreDto requestScoreDto) {
        if (!quoteRepository.findById(requestScoreDto.getQuoteId()).isPresent()) {
            throw new NoSuchElementException("Котировки с id = " + requestScoreDto.getQuoteId()
                    + " не существует");
        }
        if (scoreRepository.findById(requestScoreDto.getId()).isPresent()) {
            throw new NoSuchElementException("Счетчик с id = " + requestScoreDto.getId()
                    + " уже существует");
        } else {
            QuoteEntity quoteEntity = quoteRepository.findById(requestScoreDto.getQuoteId()).get();
            ScoreEntity scoreEntity = ScoreUtils.scoreDtoRequestToEntity(requestScoreDto, quoteEntity);
            scoreEntity.setId(UUID.randomUUID().toString());
            scoreEntity.setScoresPositive(0l);
            scoreEntity.setScoresNegative(0l);
            scoreEntity = scoreRepository.save(scoreEntity);
            return ScoreUtils.scoreEntityToDto(scoreEntity);
        }
    }

    public ResponseScoreDto update(RequestScoreDto requestScoreDto) {
        if (!scoreRepository.findById(requestScoreDto.getId()).isPresent()) {
            throw new NoSuchElementException("Счетчика с id = " + requestScoreDto.getId()
                    + " не существует");
        } else {
            ScoreEntity scoreEntity = scoreRepository.findById(requestScoreDto.getId()).get();
            scoreEntity.setQuote(quoteRepository.findById(requestScoreDto.getQuoteId()).get());
            scoreEntity.setScoresPositive(requestScoreDto.getScoresPositive());
            scoreEntity.setScoresNegative(requestScoreDto.getScoresNegative());
            scoreEntity = scoreRepository.save(scoreEntity);
            return ScoreUtils.scoreEntityToDto(scoreEntity);
        }
    }

    public ResponseScoreDto update(String id, boolean positive, boolean negative) {
        if (!scoreRepository.findById(id).isPresent()) {
            throw new NoSuchElementException("Счетчика с id = " + id
                    + " не существует");
        }
        if ((positive == false)&&(negative == false)){
            throw new NoSuchElementException("Вам необходимо воспользоваться иным методом " +
                    "update либо задать параметры в запросе");
        }else{
            ScoreEntity scoreEntity = scoreRepository.findById(id).get();
            scoreEntity.setQuote(quoteRepository.findById(id).get());
            if(positive){
                scoreEntity.setScoresPositive(scoreEntity.getScoresPositive() + 1);
            }
            if(negative){
                scoreEntity.setScoresNegative(scoreEntity.getScoresNegative() + 1);
            }
            scoreEntity = scoreRepository.save(scoreEntity);
            return ScoreUtils.scoreEntityToDto(scoreEntity);
        }
    }

    public Set<ResponseScoreDto> topOrFlop(int county, boolean top) {
        List<ScoreEntity> scoreEntityLists = scoreRepository.findAll();
        Collections.shuffle(scoreEntityLists);
        List<ScoreEntity> scoreEntityList = new ArrayList<>(scoreEntityLists);

        if(top){
            Collections.shuffle(scoreEntityList);
            Collections.sort(scoreEntityList, reverseOrder());
        }else{
            Collections.sort(scoreEntityList);
        }
        Set<ScoreEntity> scoreEntities = new HashSet<>();
        System.out.println(scoreEntityList.size());
        if((scoreEntityList.size()) <= county){
            for(int i=0; i < scoreEntityList.size(); i++) scoreEntities.add(scoreEntityList.get(i));
        }else{
            for(int i=0; i < county; i++)scoreEntities.add(scoreEntityList.get(i));
        }
        return ScoreUtils.scoreEntitiesToDto(scoreEntities);
    }

    public void delete(String id) {
        scoreRepository.deleteById(id);
    }
}
