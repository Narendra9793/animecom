
package com.animecommunity.animecom.Dao;

import com.animecommunity.animecom.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    
}
