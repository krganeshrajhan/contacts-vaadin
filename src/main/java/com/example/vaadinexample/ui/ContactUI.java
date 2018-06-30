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


    VerticalLayout main = new VerticalLayout();
    HorizontalLayout layout = new HorizontalLayout();
    Grid<Contact> grid = new Grid<>(Contact.class);
    ContactForm form = new ContactForm(this);

    Button add = new Button("Add");


    @Override
    protected void init(VaadinRequest vaadinRequest) {

        grid.setColumns("firstName", "lastName");
        layout.addComponents(grid, form);

        main.addComponents(add,layout);

        setContent(main);
    }
}
