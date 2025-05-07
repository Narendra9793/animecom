package com.animecommunity.animecom.Dao;

import com.animecommunity.animecom.Models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    
}
