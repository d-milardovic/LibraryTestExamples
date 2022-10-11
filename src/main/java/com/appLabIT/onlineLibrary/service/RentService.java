package com.appLabIT.onlineLibrary.service;

import com.appLabIT.onlineLibrary.model.Book;
import com.appLabIT.onlineLibrary.model.Rent;
import com.appLabIT.onlineLibrary.model.User;

import java.util.List;
import java.util.Set;


public interface RentService {
    List<Book> rentBook(Integer userId, List<Integer> bookId);

    List<Rent> returnBook(List<Integer> rentId);

    List<Rent> getAllUserRents(Integer userId);

    void deleteRent(Integer rentId);
}
