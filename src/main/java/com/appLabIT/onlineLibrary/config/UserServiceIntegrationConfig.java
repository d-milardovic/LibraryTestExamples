package com.appLabIT.onlineLibrary.config;

import com.appLabIT.onlineLibrary.repository.UserRepository;
import com.appLabIT.onlineLibrary.service.UserService;
import com.appLabIT.onlineLibrary.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceIntegrationConfig {
    @Bean
    UserService getUserService(UserRepository userRepository){
        return new UserServiceImpl(userRepository);}
}
