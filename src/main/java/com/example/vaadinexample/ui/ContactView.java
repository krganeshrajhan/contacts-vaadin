package com.example.vaadinexample.ui;

import com.example.vaadinexample.model.Contact;
import com.vaadin.data.Binder;
import com.vaadin.ui.Label;

import java.util.function.Consumer;
import java.util.function.Function;

import static com.example.vaadinexample.constants.ContactConstants.FIRST_NAME_IS_REQUIRED;
import static com.example.vaadinexample.constants.ContactConstants.LAST_NAME_IS_REQUIRED;

public class ContactView extends ContactDesign {

    Binder<Contact> binder = new Binder<>(Contact.class);

    public ContactView(Consumer<Contact> saveConsumer,
                       Consumer<Contact> cancelConsumer,
                       Consumer<Contact> deleteConsumer) {
        setValidations();
        binder.bindInstanceFields(this);

        save.addClickListener(e -> {
            saveConsumer.accept(binder.getBean());
        });

        cancel.addClickListener(e -> {
            cancelConsumer.accept(binder.getBean());
        });

        delete.addClickListener(e -> {
            deleteConsumer.accept(binder.getBean());
        });

    }

    public void setValidations() {
        Label firstNameStatus = new Label(FIRST_NAME_IS_REQUIRED);
        /*binder.forField(firstName).withValidator(firstName -> firstName.length()<=3, "Message").withValidationStatusHandler(status -> {
            firstNameStatus.setValue(status.getMessage().orElse(""));
            firstNameStatus.setVisible(status.isError());
        }).bind(Contact::getFirstName, Contact::setFirstName);*/
        binder.forField(lastName).asRequired(LAST_NAME_IS_REQUIRED).bind(Contact::getLastName, Contact::setLastName);
    }

    public void setContact(Contact value) {
        binder.setBean(value);
    }

    public void clearContact() {
        binder.removeBean();

    }
}
