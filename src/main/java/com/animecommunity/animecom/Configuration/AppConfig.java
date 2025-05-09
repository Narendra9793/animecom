package com.animecommunity.animecom.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
public class AppConfig {
    @Bean
    public UserDetailsService getUserDetailsService(){
        return new UserDetailsServiceImpl() ;
    }

    @Bean
    public BCryptPasswordEncoder geBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
