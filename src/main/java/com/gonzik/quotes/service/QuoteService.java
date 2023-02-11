package com.gonzik.quotes.service;

import com.gonzik.quotes.dto.RequestQuoteDto;
import com.gonzik.quotes.dto.ResponseQuoteDto;
import com.gonzik.quotes.dto.utils.QuoteUtils;
import com.gonzik.quotes.entity.QuoteEntity;
import com.gonzik.quotes.repository.QuoteRepository;
import com.gonzik.quotes.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.Collections.reverseOrder;


@Service
@Transactional
public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;

    public QuoteService(QuoteRepository quoteRepository, UserRepository userRepository) {
        this.quoteRepository = quoteRepository;
        this.userRepository = userRepository;
    }

    public ResponseQuoteDto findById(String id) {
        if (!quoteRepository.findById(id).isPresent()) {
            throw new NoSuchElementException("Задачи с указанным id = " + id + " не существует");
        } else {
            QuoteEntity quoteEntity = quoteRepository.findById(id).get();
            return QuoteUtils.quoteEntityToDto(quoteEntity);
        }
    }

    public ResponseQuoteDto create(RequestQuoteDto requestQuoteDto) {
        if (quoteRepository.findById(requestQuoteDto.getId()).isPresent()) {
            throw new NoSuchElementException("Задача с указанным id = " + requestQuoteDto.getId()
                    + " уже существует");
        }
        if (!userRepository.findById(requestQuoteDto.getUserId()).isPresent()) {
            throw new NoSuchElementException("Повторите введя корректный id пользователя");
        }
        QuoteEntity quoteEntity = new QuoteEntity();
        quoteEntity.setId(UUID.randomUUID().toString());

        quoteEntity = quoteRepository.save(quoteEntity);
        return QuoteUtils.quoteEntityToDto(quoteEntity);
    }

    public ResponseQuoteDto update(RequestQuoteDto requestQuoteDto) {
        if (!quoteRepository.findById(requestQuoteDto.getId()).isPresent()) {
            throw new NoSuchElementException("Задачи с указанным id = " + requestQuoteDto.getId()
                    + " не существует");
        } else {
            QuoteEntity quoteEntity = quoteRepository.findById(requestQuoteDto.getId()).get();
            quoteEntity.setUser(userRepository.findById(requestQuoteDto.getUserId()).get());
            quoteEntity.setQuote(requestQuoteDto.getQuote());
            quoteEntity = quoteRepository.save(quoteEntity);
            return QuoteUtils.quoteEntityToDto(quoteEntity);
        }
    }

    public Set<ResponseQuoteDto> last(int last) {
        List<QuoteEntity> quoteEntityList = quoteRepository.findAll();
        Collections.sort(quoteEntityList, reverseOrder());
        Set<QuoteEntity> quoteEntities = new HashSet<>();
        if(quoteEntityList.size()<last){
            for(int i=0; i<quoteEntityList.size(); i++){
                quoteEntities.add(quoteEntityList.get(i));
            }
        }else{
            for(int i=0; i<last; i++){
                quoteEntities.add(quoteEntityList.get(i));
            }
        }
        return QuoteUtils.quoteEntitiesToDto(quoteEntities);
    }

    public ResponseQuoteDto random() {
        List<QuoteEntity> quoteEntityList = quoteRepository.findAll();
        int random = (int)Math.floor(Math.random() * (quoteEntityList.size()));
        return QuoteUtils.quoteEntityToDto(quoteEntityList.get(random));
    }

    public void delete(String id) {
        quoteRepository.deleteById(id);
    }
}
