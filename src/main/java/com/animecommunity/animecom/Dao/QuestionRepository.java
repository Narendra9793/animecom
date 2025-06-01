package com.animecommunity.animecom.Dao;

import com.animecommunity.animecom.Models.Question;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query("SELECT q FROM Question q WHERE q.question_id = :question_Id")
    Question getQuestionByquestion_Id(@Param("question_id") int question_id);
    
}
