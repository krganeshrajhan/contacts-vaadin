package com.example.vaadinexample.service;

import com.example.vaadinexample.model.Contact;
import com.example.vaadinexample.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;


    public Contact save(Contact contact) {
        contactRepository.save(contact);
        return contact;
    }

    public List<Contact> findAll() {
        return  contactRepository.findAll();
    }

    public void delete(Contact contact) {
        contactRepository.delete(contact);
    }

    public List<Contact> findByIdOrderById(Long id) {
        return contactRepository.findByIdOrderById(id);
    }
}
