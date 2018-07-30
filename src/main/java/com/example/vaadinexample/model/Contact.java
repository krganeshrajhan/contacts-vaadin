package com.example.vaadinexample.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    private String firstName;

    @NotNull
    private String lastName;

    private String email;

    private int phoneNumber;

    @ManyToOne()
    @JoinColumn(name = "dialCode")
    private Country dialCountry;
}
