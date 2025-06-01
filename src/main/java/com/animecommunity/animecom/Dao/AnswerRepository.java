package com.animecommunity.animecom.Dao;

import com.animecommunity.animecom.Models.Answer;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {


    @Query("SELECT a FROM Answer a WHERE a.Answer_Id = :Answer_Id")
    Answer getAnswerByAnswer_Id(@Param("Answer_id") int Answer_Id);
    
}
