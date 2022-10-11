package com.appLabIT.onlineLibrary.helper;

public class Response {

    public static class User {
        public int id;
        public String name;
        public String lastName;
        public String email;
        public int borrowBookCounter;
        public int counterNewBookType;

        public User(int id, String name, String lastName, String email, int borrowBookCounter, int counterNewBookType) {
            this.id = id;
            this.name = name;
            this.lastName = lastName;
            this.email = email;
            this.borrowBookCounter = borrowBookCounter;
            this.counterNewBookType = counterNewBookType;
        }
    }

    public static class Book {
        public final int id;
        public final String name;
        public final String author;
        public final String bookType;
        public final int numberOfBooks;

        public Book(int id, String name, String author, String bookType, int numberOfBooks) {
            this.id = id;
            this.name = name;
            this.author = author;
            this.bookType = bookType;
            this.numberOfBooks = numberOfBooks;
        }
    }

}
