package com.example.vaadinexample.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @JsonProperty("dial_Code")
//    @OneToMany(mappedBy = "country")
    private String dialCode;

    private String code;

    /*@OneToMany(mappedBy = "dialCountry")
    private List<Contact> contacts;*/
}
