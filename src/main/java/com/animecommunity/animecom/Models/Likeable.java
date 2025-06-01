package com.animecommunity.animecom.Models;

public interface Likeable {
    int getLikeableId();
    void addLike(Like like);
    void removeLike(Like like);
}
