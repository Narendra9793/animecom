package com.animecommunity.animecom.Dao;

import com.animecommunity.animecom.Models.Post;
import com.animecommunity.animecom.Models.Question;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface PostRepository extends JpaRepository<Post, Integer> {



    @Query("SELECT p FROM Post p WHERE p.post_Id = :post_Id")
    Post getPostBypost_Id(@Param("post_Id") int post_Id);
    
}
