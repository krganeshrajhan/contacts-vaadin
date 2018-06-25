package com.example.vaadinexample.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
}
