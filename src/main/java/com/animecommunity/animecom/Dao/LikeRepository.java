package com.animecommunity.animecom.Dao;

import com.animecommunity.animecom.Models.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    
}
