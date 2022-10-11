package com.appLabIT.onlineLibrary.service;

import com.appLabIT.onlineLibrary.model.Book;
import com.appLabIT.onlineLibrary.model.dto.BookDto;

import java.util.Optional;

public interface BookService {
    Book addBook(BookDto bookDto);

    void deleteBook(Integer bookId);

    Optional<Book> updateBook(Integer bookId, BookDto bookDto);
}
