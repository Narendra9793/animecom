package com.animecommunity.animecom.Configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.animecommunity.animecom.Dao.UserRepository;
import com.animecommunity.animecom.Models.User;



public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user= (User)userRepository.getUserByemail(email);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password");
        }

        CustomUserDetails customUserDetails=new CustomUserDetails((com.animecommunity.animecom.Models.User) user);
        return  customUserDetails;
    }
    
}
