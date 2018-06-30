package com.example.vaadinexample.ui;

import com.example.vaadinexample.constants.ContactConstants;
import com.example.vaadinexample.model.Contact;
import com.example.vaadinexample.repository.ContactRepository;
import com.example.vaadinexample.service.ContactService;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.example.vaadinexample.constants.ContactConstants.FIRST_NAME;
import static com.example.vaadinexample.constants.ContactConstants.LAST_NAME;

@SpringUI
@Theme("valo")
public class ContactUI extends UI {

    @Autowired
    @Getter
    private ContactService contactService;
    private Button add = new Button("Add");
    private Grid<Contact> grid = new Grid<>(Contact.class);
    private ContactForm contactForm = new ContactForm(this);

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        VerticalLayout main = new VerticalLayout();
        HorizontalLayout layout1 = new HorizontalLayout();

        grid.setColumns("firstName", "lastName");
        layout1.addComponents(grid,contactForm);

        updateContacts();
        main.addComponents(add, layout1);
        setContent(main);

        contactForm.setVisible(false);
        grid.asSingleSelect().addValueChangeListener(e -> {
            if(e.getValue() == null) {
                contactForm.setVisible(false);
            } else {
                contactForm.setContact(e.getValue());
            }
        });
        add.addClickListener(e -> {
            grid.asSingleSelect().clear();
            contactForm.setContact(new Contact());
        });
    }

    public void updateContacts() {
        grid.setItems(contactService.findAll());
    }
}
