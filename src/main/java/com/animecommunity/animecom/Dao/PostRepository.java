package com.animecommunity.animecom.Dao;

import com.animecommunity.animecom.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
    
}
