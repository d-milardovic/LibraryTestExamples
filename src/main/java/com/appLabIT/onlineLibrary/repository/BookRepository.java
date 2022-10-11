package com.appLabIT.onlineLibrary.repository;

import com.appLabIT.onlineLibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
