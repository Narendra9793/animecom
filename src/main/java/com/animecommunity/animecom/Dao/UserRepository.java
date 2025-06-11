package com.animecommunity.animecom.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animecommunity.animecom.Models.Roles;
import com.animecommunity.animecom.Models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserByusername(String username);

    User getUserByemail(String email);

    User getUserByrole(Roles role);

    
}