package com.gonzik.quotes.repository;

import com.gonzik.quotes.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository  extends JpaRepository<ScoreEntity, String> {
}
