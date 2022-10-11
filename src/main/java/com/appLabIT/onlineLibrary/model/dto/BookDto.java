package com.appLabIT.onlineLibrary.model.dto;

import com.appLabIT.onlineLibrary.model.enums.BookType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String name;
    private String author;
    private Integer numberOfBooks;
    private BookType bookType;
}
