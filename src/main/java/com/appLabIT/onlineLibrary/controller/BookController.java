package com.appLabIT.onlineLibrary.controller;

import com.appLabIT.onlineLibrary.model.Book;
import com.appLabIT.onlineLibrary.model.User;
import com.appLabIT.onlineLibrary.model.dto.BookDto;
import com.appLabIT.onlineLibrary.model.dto.UserDto;
import com.appLabIT.onlineLibrary.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/library")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public Book addBook(@RequestBody BookDto bookDto){
        return bookService.addBook(bookDto);
    }

    @RequestMapping(value = "/deleteBook/{bookId}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable Integer bookId){
        bookService.deleteBook(bookId);
    }

    @RequestMapping(value = "updateBook/{bookId}", method = RequestMethod.PUT)
    public Optional<Book> updateBook(@PathVariable Integer bookId, @RequestBody BookDto bookDto){
        return bookService.updateBook(bookId, bookDto);
    }
}
