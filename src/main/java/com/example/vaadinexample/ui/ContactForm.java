package com.example.vaadinexample.ui;

import com.example.vaadinexample.model.Contact;
import com.example.vaadinexample.service.ContactService;
import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Email;
import java.util.List;

import static com.vaadin.ui.themes.ValoTheme.BUTTON_DANGER;
import static com.vaadin.ui.themes.ValoTheme.BUTTON_PRIMARY;

public class ContactForm extends FormLayout {

    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last Name");
    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");
    private Button delete = new Button("Delete");
    private ContactUI contactUI;
    private Binder<Contact> binder = new Binder<>(Contact.class);
    private Contact contact;
    private final VerticalLayout popupWindow = new VerticalLayout();
    private PopupView confirm = new PopupView("Confirm", popupWindow);

    @Autowired
    private ContactService contactService;

    public ContactForm(ContactUI contactUI) {
        this.contactUI = contactUI;
        HorizontalLayout buttons = new HorizontalLayout(save, cancel, delete);
        addComponents(firstName, lastName, buttons);
        binder.bindInstanceFields(this);

        save.setStyleName(BUTTON_PRIMARY);
        save.setIcon(VaadinIcons.PLUS_CIRCLE);
        save.addClickListener(e -> save());

        cancel.addClickListener(e -> cancel());

        delete.setStyleName(BUTTON_DANGER);
        delete.setIcon(VaadinIcons.MINUS_CIRCLE);
        delete.addClickListener(e -> delete());

        binder.addValueChangeListener(e -> {
            cancel.setVisible(true);
        });
        confirm.setPopupVisible(false);
        confirm.addPopupVisibilityListener(e -> {
        });
    }

    public void setContact(Contact contact) {
        this.contact = contact;
        binder.setBean(contact);
        delete.setVisible(contact.getId() != null);
        cancel.setVisible(false);
        setVisible(true);
    }

    public void save() {
        System.out.println(contactService + " " + contact);
        contactUI.getContactService().save(contact);
        contactUI.updateContacts();
        setVisible(false);
        Notification.show("Contact Saved");
    }

    public void cancel() {

        if (contact.getId() != null) {
            List<Contact> contacts = contactUI.getContactService().findByIdOrderById(contact.getId());
            binder.setBean(contacts.get(0));
        } else {
            binder.setBean(new Contact());
        }
        cancel.setVisible(false);

    }

    public void delete() {

        final Window sub = new Window("Confirm");
        sub.setModal(true);
        sub.setResizable(false);
        sub.setClosable(false);
        sub.setWidth(300.0f, Unit.PIXELS);
        sub.setHeight(300.0f, Unit.PIXELS);
        sub.center();
        VerticalLayout content = new VerticalLayout();
        content.addComponent(new Label("Are you sure, you want to remove?"));
        content.addComponent(new Button("Ok", e -> {
            contactUI.getContactService().delete(contact);
            contactUI.updateContacts();
            setVisible(false);
            sub.close();
            Notification.show("Contact Removed");
        }));
        sub.setContent(content);

        UI.getCurrent().addWindow(sub);
    }
}
