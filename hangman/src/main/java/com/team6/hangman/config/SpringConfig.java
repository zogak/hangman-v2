package com.team6.hangman.config;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.team6.hangman.repository.JpaUserRepository;
import com.team6.hangman.repository.UserRepository;
import com.team6.hangman.service.UserService;

@Configuration
public class SpringConfig {
	
	private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }

    @Bean
    public UserService userService(){
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository(){
        return new JpaUserRepository(em);
    }

}
