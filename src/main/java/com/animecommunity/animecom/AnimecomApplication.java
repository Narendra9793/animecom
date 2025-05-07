package com.animecommunity.animecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@ComponentScan(basePackages = "com.animecommunity.animecom")
@EnableJpaRepositories(basePackages ="com.animecommunity.animecom.Dao")
@EntityScan(basePackages = "com.animecommunity.animecom.Models")
public class AnimecomApplication {

	 public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load(); // Ignore if .env is missing

        setSystemProperty("DATASOURCE_URL", dotenv);
        setSystemProperty("DATASOURCE_USER", dotenv);
        setSystemProperty("DATASOURCE_PASSWORD", dotenv);
        setSystemProperty("FRONTEND_URL", dotenv);

        SpringApplication.run(AnimecomApplication.class, args);
    }

    private static void setSystemProperty(String key, Dotenv dotenv) {
        String value = dotenv.get(key);
        if (value != null && !value.isEmpty()) {
            System.setProperty(key, value);
            System.out.println(key +"-------------------------------- "+ System.getProperty(key));
        } else {
            System.err.println("Warning: Environment variable " + key + " is missing or empty!");
        }
    }

}
