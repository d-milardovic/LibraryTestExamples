package com.appLabIT.onlineLibrary.helper;

public class Request {

    public static class User {
        public final String name;
        public final String lastName;
        public final String email;

        private User(String name, String lastName, String email) {
            this.name = name;
            this.lastName = lastName;
            this.email = email;
        }

        public static User create(String name, String lastName, String email) {
            return new User(name, lastName, email);
        }
    }

    public static class Book {
        public final String name;
        public final String author;
        public final String bookType;

        private Book(String name, String author, String bookType) {
            this.name = name;
            this.author = author;
            this.bookType = bookType;
        }

        public static Book newBook(String name, String author) {
            return new Book(name, author, "New");
        }

        public static Book classicBook(String name, String author) {
            return new Book(name, author, "Classic");
        }

        public static Book standardBook(String name, String author) {
            return new Book(name, author, "Standard");
        }

    }
}
