package com.animecommunity.animecom.Dao;


import com.animecommunity.animecom.Models.Question;
import com.animecommunity.animecom.Models.Theory;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface TheoryRepository extends JpaRepository<Theory, Integer> {

    @Query("SELECT t FROM Theory t WHERE t.theory_Id = :theory_Id")
    Question getTheoryBytheory_Id(@Param("theory_Id") int theory_Id);
    
}
