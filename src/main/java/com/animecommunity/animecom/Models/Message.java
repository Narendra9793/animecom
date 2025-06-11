package com.animecommunity.animecom.Models;

import java.time.LocalDateTime;

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
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int messageId;
    private int senderId;
    private int recieverId;
    private String messageContent;
    private Room room;
    private LocalDateTime timeStamp;

    public Message(String messageContent){
        this.messageContent=messageContent;
        this.timeStamp=LocalDateTime.now();
    }
}
