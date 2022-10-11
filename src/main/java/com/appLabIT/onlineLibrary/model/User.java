package com.appLabIT.onlineLibrary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Min;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastName;
    private String email;
    @Min(value = 0, message = "Can't be under zero!")
    private Integer borrowBookCounter;
    @Min(value = 0, message = "Can't be under zero!")
    private Integer counterNewBookType;

}
