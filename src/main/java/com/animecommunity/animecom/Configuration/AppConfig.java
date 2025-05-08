package com.animecommunity.animecom.Configuration;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
