package com.animecommunity.animecom.Models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`USER`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    // Personal Information
    @NotBlank(message = "First name cannot be blank.")
    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters.")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank.")
    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters.")
    private String lastName;

    @NotBlank(message = "Username cannot be blank and should be unique.")
    @Column(unique = true)
    private String username;


    @NotBlank(message = "Date of birth cannot be blank.")
    private String dob;


    // Contact Information
    @NotBlank(message = "Email cannot be blank.")
    @Column(unique = true)
    private String email;

    private String password;
    // Photographs
    private String imageUrl1;
    private String imageUrl2;
    private String imageUrl3;
    private String imageUrl4;


    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.LAZY, mappedBy = "user",  orphanRemoval = true)
    private List<Theory> theories = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    List<Post> posts = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    List<Question> questions = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    List<Answer> answers = new ArrayList<>();


    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    List<Post> savedposts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Roles role;



}
