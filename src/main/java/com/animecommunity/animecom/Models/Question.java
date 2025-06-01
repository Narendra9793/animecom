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

@Table(name = "`QUESTION`")
@Entity
public class Question implements Likeable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int question_id;
    private String question_img_url;


    @ManyToOne
    @JoinColumn(name = "user_id") 
    @JsonBackReference
    private User user;
    

    @NotBlank(message = "Question cannot be blank.")
    private String question_statement;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.LAZY, mappedBy = "question",  orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.LAZY, mappedBy = "question",  orphanRemoval = true)
    List<Like> likes = new ArrayList<>();

    @Override
    public void addLike(Like like) {
        likes.add(like);
    }

    @Override
    public int getLikeableId() {
        return this.getQuestion_id();
    }


    public Likeable orElseThrowLikeable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    }

    @Override
    public void removeLike(Like like) {
        likes.remove(like);
    }
}
