package com.animecommunity.animecom.Models;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

@Table(name = "`LIKES`")
@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "like_Id")
    private int likeId;

    @ManyToOne
    @JsonBackReference
    private User user;

    private String targetId;
    private String targetType;

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
    @JoinColumn(name = "post_id")
    private Post post;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private LocalDateTime likeAt;

    public Like(){
        this.likeAt=LocalDateTime.now();
    }


}
