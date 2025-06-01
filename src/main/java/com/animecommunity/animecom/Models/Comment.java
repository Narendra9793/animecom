package com.animecommunity.animecom.Models;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "`COMMENT`")
@Entity
public class Comment implements Likeable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_Id")
    private int commentId;
    private String comment_img_url;
    private String commentBody;    


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "Answer_id")
    private Answer answer;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "theory_id")
    private Theory theory;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;


    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.LAZY, mappedBy = "comment",  orphanRemoval = true)
    List<Like> likes = new ArrayList<>();

    @Override
    public void addLike(Like like) {
        likes.add(like);
    }

    @Override
    public int getLikeableId() {
        return this.getCommentId();
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
