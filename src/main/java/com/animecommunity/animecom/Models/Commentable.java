package com.animecommunity.animecom.Models;

public interface Commentable {
    int getCommentableId();
    void addComment(Comment comment);
    void removeComment(Comment comment);
}
