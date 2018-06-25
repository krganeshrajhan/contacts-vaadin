package com.example.vaadinexample;

import com.example.vaadinexample.model.Contact;
import com.example.vaadinexample.repository.ContactRepository;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VaadinExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaadinExampleApplication.class, args);
	}

	@Autowired
	private ContactRepository contactRepository;



	@Bean
	public CommandLineRunner loadData() {
		return (args -> {
			contactRepository.save(new Contact(null, "Ganeshrajhan", "Rajarethinam"));
		});
	}
}
