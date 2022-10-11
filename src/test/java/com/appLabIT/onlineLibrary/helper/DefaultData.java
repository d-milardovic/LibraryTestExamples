package com.appLabIT.onlineLibrary.helper;

import java.util.*;

public class DefaultData {

    public static List<Request.Book> BOOKS = List.of(
            Request.Book.standardBook("Sunreach", "Brandon Sanderson"),
            Request.Book.standardBook("ReDawn", "Brandon Sanderson"),
            Request.Book.newBook("Evershore", "Brandon Sanderson"),

            Request.Book.standardBook("We are Legion", "Dennis E. Taylor"),
            Request.Book.newBook("Feedback", "Dennis E. Taylor"),

            Request.Book.newBook("Existentially Challenged", "Yahtzee Croshaw"),
            Request.Book.standardBook("Differently Morphous", "Yahtzee Croshaw"),
            Request.Book.standardBook("Will Save the Galaxy for Food", "Yahtzee Croshaw"),

            Request.Book.standardBook("Philosopher's Stone", "J. K. Rowling"),
            Request.Book.standardBook("Chamber of Secrets", "J. K. Rowling"),
            Request.Book.standardBook("Prisoner of Azkaban", "J. K. Rowling"),
            Request.Book.standardBook("Goblet of Fire", "J. K. Rowling"),
            Request.Book.standardBook("Order of the Phoenix", "J. K. Rowling"),
            Request.Book.standardBook("Half-Blood Prince", "J. K. Rowling"),
            Request.Book.standardBook("Deathly Hallows", "J. K. Rowling"),

            Request.Book.classicBook("The Fellowship of the Ring", "J. R. R. Tolkien"),
            Request.Book.classicBook("The Two Towers", "J. R. R. Tolkien"),
            Request.Book.classicBook("The Return of the King", "J. R. R. Tolkien")
    );

    public static List<Request.User> USERS = List.of(
            Request.User.create("Tea", "Milardovic", "tea.milardovic@epiceros.com"),
            Request.User.create("Domagoj", "Milardovic", "domagoj.milardovic@epiceros.com"),
            Request.User.create("Markus", "Olsson", "markus.olsson@epiceros.com")
    );

}
