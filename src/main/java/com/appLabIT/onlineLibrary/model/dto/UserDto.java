package com.appLabIT.onlineLibrary.model.dto;

import com.appLabIT.onlineLibrary.model.enums.BookType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String name;
    private String lastName;
    private String email;
    private Integer borrowBookCounter;
    private BookType bookType;
    private Integer counterNewBookType;
}
