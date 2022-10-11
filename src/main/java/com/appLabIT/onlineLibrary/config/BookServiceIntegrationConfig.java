package com.appLabIT.onlineLibrary.config;

import com.appLabIT.onlineLibrary.repository.BookRepository;
import com.appLabIT.onlineLibrary.service.BookService;
import com.appLabIT.onlineLibrary.service.impl.BookServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookServiceIntegrationConfig {
    @Bean
    BookService getBookService(BookRepository bookRepository){
        return new BookServiceImpl(bookRepository);}
}
