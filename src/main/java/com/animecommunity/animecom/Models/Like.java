package com.animecommunity.animecom.Models;

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
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "`LIKES`")
@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "like_Id")
    private int likeId;

    @ManyToOne
    @JoinColumn(name = "user_id") 
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id") 
    @JsonBackReference
    private Question question;

    @ManyToOne
    @JoinColumn(name = "theory_id") 
    @JsonBackReference
    private Theory theory;

    @ManyToOne
    @JoinColumn(name = "answer_id") 
    @JsonBackReference
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "post_id") // This creates the foreign key column in the `COMMENT` table
    @JsonBackReference
    private Post post;
}
