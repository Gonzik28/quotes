package com.gonzik.quotes.repository;

import com.gonzik.quotes.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository  extends JpaRepository<QuoteEntity, String> {
}
