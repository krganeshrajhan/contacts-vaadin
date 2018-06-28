package com.example.vaadinexample.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class ContactForm extends FormLayout {

    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last Name");
    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");
    private Button delete = new Button("Delete");

    private ContactUI contactUI;

    public ContactForm(ContactUI contactUI) {

        this.contactUI = contactUI;
        HorizontalLayout layout = new HorizontalLayout(save, cancel);
        addComponents(firstName,lastName,layout);
    }

}
