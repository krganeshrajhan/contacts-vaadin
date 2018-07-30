package com.example.vaadinexample.repository;

import com.example.vaadinexample.model.Contact;
import com.example.vaadinexample.model.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ContactRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void testCreate() {
        Country country = countryRepository.save(new Country(null,"India", "+91", "IN"));
        Contact contact = new Contact(null, "Ganeshrajhan", "Rajarethinam", "krganeshrajhan@gmail.com", 98254760, country);
        contact = contactRepository.save(contact);
        assertNotNull(contact.getId());
    }
}
