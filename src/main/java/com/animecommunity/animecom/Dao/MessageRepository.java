package com.animecommunity.animecom.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.animecommunity.animecom.Models.Message;
import com.animecommunity.animecom.Models.Room;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findByRoom (Room room);
}