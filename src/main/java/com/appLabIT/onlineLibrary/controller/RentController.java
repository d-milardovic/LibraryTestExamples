package com.appLabIT.onlineLibrary.controller;

import com.appLabIT.onlineLibrary.model.Book;
import com.appLabIT.onlineLibrary.model.Rent;
import com.appLabIT.onlineLibrary.model.User;
import com.appLabIT.onlineLibrary.service.RentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/library")
public class RentController {

    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @RequestMapping(value = "/rentBook/user/{userId}", method = RequestMethod.POST)
    public List<Book> userBorrowBook(@PathVariable Integer userId, @RequestBody List<Integer> bookId){
        return rentService.rentBook(userId, bookId);
    }
    @RequestMapping(value = "/rentBack", method = RequestMethod.PUT)
    public List<Rent> returnBook(@RequestBody List<Integer> rentId){
        return rentService.returnBook(rentId);
    }

    @RequestMapping(value = "/allRents/{userId}", method = RequestMethod.GET)
    public List<Rent> getAllUserRents(@PathVariable Integer userId){
        return rentService.getAllUserRents(userId);
    }
    @RequestMapping(value = "/deleteRent/{rentId}", method = RequestMethod.DELETE)
    public void deleteRent(@PathVariable Integer rentId){ rentService.deleteRent(rentId);}

    //TODO: get days between two instants
    // https://stackoverflow.com/questions/55779996/calculate-days-hours-and-minutes-between-two-instants
}
