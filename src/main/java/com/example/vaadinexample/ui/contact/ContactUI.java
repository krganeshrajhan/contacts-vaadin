package com.example.vaadinexample.ui.contact;

import com.example.vaadinexample.model.Contact;
import com.example.vaadinexample.service.ContactService;
import com.example.vaadinexample.ui.menu.MenuFormFactory;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = "/contact")
@Title("Contacts")
@Theme("valo")
public class ContactUI extends UI {

    @Autowired
    @Getter
    private ContactService contactService;

    @Autowired
    private MenuFormFactory menuFormFactory;

    private Button add = new Button("Add");
    private Grid<Contact> grid = new Grid<>(Contact.class);
    private ContactForm contactForm = new ContactForm(this);

    private Panel logoPanel = new Panel();

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        VerticalLayout main = new VerticalLayout();
        Component menu = menuFormFactory.createComponent();
        HorizontalLayout layout1 = new HorizontalLayout();

        grid.setColumns("firstName", "lastName", "dob");
        layout1.addComponents(grid,contactForm);

        logoPanel.setWidth("5%");
        logoPanel.setHeight("5%");
        logoPanel.setContent(new Label("Lo"));

        updateContacts();
        main.addComponents(/*logoPanel,*/menu,add, layout1);
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
