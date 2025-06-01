package com.animecommunity.animecom.Models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "`POST`")
@Entity
public class Post implements Likeable, Commentable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int post_Id;
    @NotBlank(message = "Post image url can not be blank")
    private String post_img_url;
    private String post_description;
    

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.LAZY, mappedBy = "post",  orphanRemoval = true)
    List<Like> likes = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.LAZY, mappedBy = "post",  orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Override
    public void addLike(Like like) {
        likes.add(like);
    }

    @Override
    public int getLikeableId() {
        return this.getPost_Id();
    }

    public Commentable orElseThrowCommentable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    }

    public Likeable orElseThrowLikeable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    }

    @Override
    public void removeLike(Like like) {
        likes.remove(like);
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public int getCommentableId() {
        return this.getPost_Id();
    }

    @Override
    public void removeComment(Comment comment) {
        comments.remove(comment);
    }


}