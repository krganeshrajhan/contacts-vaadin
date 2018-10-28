package com.example.vaadinexample.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Contact {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Enter a value")
    private String firstName;

    @NotNull
    private String lastName;

    private String email;

    private int phoneNumber;

    @ManyToOne()
    @JoinColumn(name = "dialCode")
    private Country dialCountry;

    private LocalDate dob;
}
