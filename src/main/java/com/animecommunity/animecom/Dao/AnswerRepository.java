package com.animecommunity.animecom.Dao;

import com.animecommunity.animecom.Models.Answer;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    Answer findByanswerId(int answer_Id);
    
}
