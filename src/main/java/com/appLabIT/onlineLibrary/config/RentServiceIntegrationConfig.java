package com.appLabIT.onlineLibrary.config;

import com.appLabIT.onlineLibrary.repository.BookRepository;
import com.appLabIT.onlineLibrary.repository.RentRepository;
import com.appLabIT.onlineLibrary.repository.UserRepository;
import com.appLabIT.onlineLibrary.service.RentService;
import com.appLabIT.onlineLibrary.service.impl.RentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentServiceIntegrationConfig {
    @Bean
    RentService getRentService(RentRepository rentRepository, UserRepository userRepository, BookRepository bookRepository){
        return new RentServiceImpl(rentRepository,userRepository,bookRepository);}
}
