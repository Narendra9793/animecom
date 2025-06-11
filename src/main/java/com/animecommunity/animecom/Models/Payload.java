package com.animecommunity.animecom.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payload {
    private int senderId;
    private int recieverId;
    private String messageContent;
}
