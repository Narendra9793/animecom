package com.animecommunity.animecom.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MESSAGE")
public class Messa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roomId;

    private int userId;
    private int AdminId;
    private String roomKey;
}
