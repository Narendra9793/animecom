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

@Table(name = "`ANSWER`")
@Entity
public class Answer implements Likeable, Commentable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Answer_id;
    private String answer_img_url;

    @ManyToOne
    @JoinColumn(name = "user_id") 
    @JsonBackReference
    private User user;
    

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonBackReference
    private Question question;

    @NotBlank(message = "Answer cannot be blank.")
    private String answer_statement;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.LAZY, mappedBy = "answer",  orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.LAZY, mappedBy = "answer",  orphanRemoval = true)
    List<Like> likes = new ArrayList<>();

    @Override
    public void addLike(Like like) {
        likes.add(like);
    }

    @Override
    public int getLikeableId() {
        return this.getAnswer_id();
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
        return this.getAnswer_id();
    }

    @Override
    public void removeComment(Comment comment) {
        comments.remove(comment);
    }


}
