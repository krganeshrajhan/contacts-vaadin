package com.example.vaadinexample;

import com.example.vaadinexample.model.Contact;
import com.example.vaadinexample.model.Country;
import com.example.vaadinexample.repository.ContactRepository;
import com.example.vaadinexample.repository.CountryRepository;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class VaadinExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaadinExampleApplication.class, args);
	}

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private CountryRepository countryRepository;



	@Bean
	public CommandLineRunner loadData() {
		return (args -> {
			Country country = countryRepository.save(new Country(null,"India", "+91", "IN"));

			contactRepository.save(new Contact(null, "Ganeshrajhan", "Rajarethinam", "krganeshrajhan@gmail.com", 98254760, country));
		});
	}
}
