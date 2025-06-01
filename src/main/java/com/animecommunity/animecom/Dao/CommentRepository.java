
package com.animecommunity.animecom.Dao;

import com.animecommunity.animecom.Models.Comment;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("SELECT c FROM Comment c WHERE c.commentId = :commentId")
    Comment getCommentBycommentId(@Param("commentId") int commentId);
    
    
}
