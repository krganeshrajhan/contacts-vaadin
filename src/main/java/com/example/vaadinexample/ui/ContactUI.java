package com.example.vaadinexample.ui;

import com.example.vaadinexample.model.Contact;
import com.example.vaadinexample.repository.ContactRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringUI
@Theme("valo")
public class ContactUI extends UI {


    @Autowired
    private ContactRepository contactRepository;

    Button add = new Button("Add");
    Panel panel = new Panel("Contacts");
    HorizontalSplitPanel hs = new HorizontalSplitPanel();
    Grid<Contact> grid = new Grid<>(Contact.class);
    /*ContactView view = new ContactView(
            contact -> saveContact(contact),
            contact -> clearContact(contact),
            contact -> removeContact(contact)
    );*/
    ContactForm contactForm = new ContactForm(this);

    public void removeContact(Contact contact) {
        contactRepository.delete(contact);
        listContacts();
        List<Contact> contacts = getAllContacts();
        if(!contacts.isEmpty()) {
            grid.select(contacts.get(0));
        }
    }

    public void clearContact(Contact contact) {
        List<Contact> origins = contactRepository.findByIdOrderById(contact.getId());
        /*if(!origins.isEmpty()) {
            setContact(origins.get(0));
        }*/
    }

    private void saveContact(Contact contact) {
        System.out.print(contact.toString());
        contactRepository.save(contact);
        listContacts();
        grid.select(contact);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }


    @Override
    protected void init(VaadinRequest vaadinRequest) {


        add.setIcon(VaadinIcons.PLUS);
        /*add.addClickListener(e -> {
            view.setContact(new Contact());
        });*/

        panel.setSizeFull();
        hs.setSizeFull();
        VerticalLayout vl = new VerticalLayout();
        vl.addComponent(add);
        vl.addComponent(grid);
        grid.setColumns("firstName", "lastName");
        listContacts();

        hs.setFirstComponent(vl);


        hs.setSecondComponent(contactForm);
        /*grid.asSingleSelect().addValueChangeListener(e -> {
            setContact(e.getValue());
        });*/

        panel.setContent(hs);
        setContent(panel);

    }

    /*public void setContact(Contact value) {
        view.setContact(value);
    }*/

    private void listContacts() {
        grid.setItems(getAllContacts());
    }
}
