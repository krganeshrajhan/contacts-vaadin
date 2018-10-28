package com.example.vaadinexample.repository;

import com.example.vaadinexample.model.Contact;
import com.example.vaadinexample.model.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.example.vaadinexample")
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
        Contact contact = new Contact(null, "Ganeshrajhan", "Rajarethinam", "krganeshrajhan@gmail.com", 98254760, country, LocalDate.now());
        contact = contactRepository.save(contact);
        assertNotNull(contact.getId());
    }

    @Test
    public void testGetDob() {
        Country country = countryRepository.save(new Country(null,"Singapore", "+65", "IN"));
        Contact contact = new Contact(null, "Ganeshrajhan", "Rajarethinam", "krganeshrajhan@gmail.com", 98254760, country, LocalDate.now());
        contact = contactRepository.save(contact);
//        contactRepository.findAll();
        LocalDate dt = LocalDate.now();
        dt.toString();
        List<Contact> c1 = contactRepository.findByDob(10, 28);
        List<Contact> c2 = contactRepository.findByDob(10, 27);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<Contact> contactList = contactRepository.findAll((root, query, cb) ->
                cb.and(
                        cb.equal(root.get("dob"), LocalDate.now())
//                        cb.equal(root.get("dob").format(formatter), LocalDate.now().format(formatter))
                        /*cb.greaterThanOrEqualTo(root.get(), zeroHour),
                        cb.lessThan(root.get(Employee_.joiningDate), zeroHour.plusDays(1))*/
                )
        );
        assertNotNull(contactList);
    }
}
