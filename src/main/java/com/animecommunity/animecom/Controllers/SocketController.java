package com.animecommunity.animecom.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.animecommunity.animecom.Dao.MessageRepository;
import com.animecommunity.animecom.Dao.RoomRepository;
import com.animecommunity.animecom.Dao.UserRepository;
import com.animecommunity.animecom.Models.Message;
import com.animecommunity.animecom.Models.Payload;
import com.animecommunity.animecom.Models.Roles;
import com.animecommunity.animecom.Models.Room;
import com.animecommunity.animecom.Models.User;

@RestController("/app")
public class SocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MessageRepository messageRepositor;

    @Autowired
    private UserRepository userRepository;

    // app/incommingMessage    
    @MessageMapping("/incommingMessage/{roomKey}")
    public void handleIncommingMessage(@PathVariable String roomKey, @RequestBody Payload payload, Principal principal) {
        Room room= this.roomRepository.getRoomsBySenderReceiver(payload.getSenderId(), payload.getRecieverId()).get(0);
        if(room == null){
            room = new Room();
            User admin=this.userRepository.getUserByrole(Roles.ADMIN);
            User user=this.userRepository.getUserByusername(principal.getName());

            room.setParticepentA(admin.getUserId());
            room.setParticepentB(user.getUserId());
            room.setRoomKey(admin.getUserId()+"_"+user.getUserId());

        }

        Message message = new Message(payload.getMessageContent());
        message.setRecieverId(payload.getRecieverId());
        message.setSenderId(payload.getSenderId());
        message.setRoom(room);

        this.messageRepositor.save(message);
        messagingTemplate.convertAndSend("/queue/chat/" + room.getRoomKey(), message);
    }

}
