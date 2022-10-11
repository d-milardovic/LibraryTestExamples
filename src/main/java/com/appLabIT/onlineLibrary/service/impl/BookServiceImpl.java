package com.appLabIT.onlineLibrary.service.impl;

import com.appLabIT.onlineLibrary.model.Book;
import com.appLabIT.onlineLibrary.model.dto.BookDto;
import com.appLabIT.onlineLibrary.repository.BookRepository;
import com.appLabIT.onlineLibrary.service.BookService;

import java.util.HashSet;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public Book addBook(BookDto bookDto) {

        Book newBook = new Book();
        newBook.setName(bookDto.getName());
        newBook.setAuthor(bookDto.getAuthor());
        newBook.setNumberOfBooks(bookDto.getNumberOfBooks());
        newBook.setBookType(bookDto.getBookType());
        bookRepository.save(newBook);
        return newBook;
    }

    @Override
    public void deleteBook(Integer bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public Optional<Book> updateBook(Integer bookId, BookDto bookDto) {
        Optional<Book> updateBook = bookRepository.findById(bookId);

        updateBook.get().setName(bookDto.getName());
        updateBook.get().setAuthor(bookDto.getAuthor());
        updateBook.get().setNumberOfBooks(bookDto.getNumberOfBooks());
        updateBook.ifPresent(bookRepository::save);
        return updateBook;
    }
}
