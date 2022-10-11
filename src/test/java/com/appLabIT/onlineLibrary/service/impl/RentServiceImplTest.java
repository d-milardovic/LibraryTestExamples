package com.appLabIT.onlineLibrary.service.impl;

import com.appLabIT.onlineLibrary.model.*;
import com.appLabIT.onlineLibrary.model.enums.*;
import com.appLabIT.onlineLibrary.repository.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RentServiceImplTest {

    RentRepository rentRepository = mock(RentRepository.class);
    UserRepository userRepository = mock(UserRepository.class);
    BookRepository bookRepository = mock(BookRepository.class);

    RentServiceImpl service = new RentServiceImpl(rentRepository, userRepository, bookRepository);

    @Test
    public void testRentBook_with_7_books_already_should_not_allow_new_rent() {
        assertThrows(IllegalArgumentException.class, () -> {
            // Given:
            var mockUser = new User(
                    1,
                    "markus",
                    "olsson",
                    "markus.olsson@epiceros.com",
                    7,
                    0
            );
            var mockBook = new Book(
                    45,
                    "Feedback",
                    "Dennis E. Taylor",
                    1,
                    BookType.Standard
            );
            when(userRepository.findById(1)).thenReturn(Optional.of(mockUser));
            when(bookRepository.findAllById(List.of(45))).thenReturn(List.of(mockBook));

            // When:
            service.rentBook(1, List.of(45));

            // Then:
            verify(userRepository).findById(1);
            verify(bookRepository).findAllById(List.of(45));
        });
    }
}