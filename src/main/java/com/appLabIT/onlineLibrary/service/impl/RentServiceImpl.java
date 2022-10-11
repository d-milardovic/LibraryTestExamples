package com.appLabIT.onlineLibrary.service.impl;

import com.appLabIT.onlineLibrary.model.Book;
import com.appLabIT.onlineLibrary.model.Rent;
import com.appLabIT.onlineLibrary.model.User;
import com.appLabIT.onlineLibrary.model.enums.BookType;
import com.appLabIT.onlineLibrary.repository.BookRepository;
import com.appLabIT.onlineLibrary.repository.RentRepository;
import com.appLabIT.onlineLibrary.repository.UserRepository;
import com.appLabIT.onlineLibrary.service.RentService;
import org.springframework.transaction.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class RentServiceImpl implements RentService {
    private final RentRepository rentRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public RentServiceImpl(RentRepository rentRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.rentRepository = rentRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> rentBook(Integer userId, List<Integer> bookId) {

        var existingUser = userRepository.findById(userId)
                .orElseThrow(IllegalArgumentException::new);

        var existingBooks = bookRepository.findAllById(bookId);

        if(existingBooks.size() < 6){
            for (Book borrowed : existingBooks) {
                Rent newRent = new Rent(existingUser, borrowed);
                if(isBookAvailable(borrowed) && userBookCounter(existingUser)
                        && checkBookType(borrowed, existingUser) ) {
                    borrowed.setNumberOfBooks(borrowed.getNumberOfBooks() - 1);
                    bookRepository.save(borrowed);
                    existingUser.setBorrowBookCounter(existingUser.getBorrowBookCounter() + 1);
                    userRepository.save(existingUser);
                    newRent.setStartRent(Instant.now());
                    rentRepository.save(newRent);
                }
            }
        }else {
            throw new IllegalArgumentException("You can't borrow more then five books!");
        }
        return existingBooks;
    }
    @Override
    public void deleteRent(Integer rentId) {
        var rent = rentRepository.findById(rentId)
                .orElseThrow(IllegalArgumentException::new);

        rent.getBook().setNumberOfBooks(rent.getBook().getNumberOfBooks() + 1);
        bookRepository.save(rent.getBook());
        if(rent.getBook().getBookType() == BookType.New){
            rent.getUser().setCounterNewBookType(rent.getUser().getCounterNewBookType() - 1);
        }
        rent.getUser().setBorrowBookCounter(rent.getUser().getBorrowBookCounter() - 1);
        userRepository.save(rent.getUser());
        rentRepository.deleteById(rentId);
    }

    @Override
    public List<Rent> returnBook(List<Integer> rentId) {
        var existingRent = rentRepository.findAllById(rentId);
        for (Rent rent : existingRent) {
            rent.getBook().setNumberOfBooks(rent.getBook().getNumberOfBooks() + 1);
            bookRepository.save(rent.getBook());
            if(rent.getBook().getBookType() == BookType.New){
                rent.getUser().setCounterNewBookType(rent.getUser().getCounterNewBookType() - 1);
            }
            rent.getUser().setBorrowBookCounter(rent.getUser().getBorrowBookCounter() - 1);
            userRepository.save(rent.getUser());
            rent.setEndRent(Instant.now());
            rentRepository.save(rent);

            Duration duration = Duration.between(rent.getStartRent(), rent.getEndRent());
            if(duration.toDays() > 30){
                throw new IllegalArgumentException("You need to pay penalty for late return!");
            }
        }
        return existingRent;
    }

    @Override
    public List<Rent> getAllUserRents(Integer userId) {
        return rentRepository.findByUserId(userId);
    }

    public boolean isBookAvailable(Book book){
        if(book.getNumberOfBooks() == 0){
            throw new IllegalArgumentException("This book is not available");
        }
        return true;
    }
    public boolean userBookCounter(User user){
        if(user.getBorrowBookCounter() == 7){
            throw new IllegalArgumentException("You can't borrow new book because you already borrowed 7 books");
        }
        return true;
    }
    public boolean checkBookType(Book book, User user){
        int allTypeCounter = user.getCounterNewBookType();
        if(book.getBookType() == BookType.New){
            allTypeCounter++;
        }
        if (allTypeCounter > 2){
            throw new IllegalArgumentException("You can have just two books type New!");
        }user.setCounterNewBookType(allTypeCounter);
        userRepository.save(user);
        return true;
    }

}
