package com.example.vaadinexample.ui.login;

import com.example.vaadinexample.service.login.RegisterService;
import com.example.vaadinexample.ui.base.BaseForm;
import com.example.vaadinexample.ui.base.BaseFormFactory;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import static com.vaadin.ui.themes.ValoTheme.BUTTON_FRIENDLY;

@org.springframework.stereotype.Component
public class SignupFormFactory implements BaseFormFactory {

    @Autowired
    private RegisterService registerService;

    @Override
    public Component createComponent() {
        return new SignupForm().init().layout();
    }

    private class SignupForm implements BaseForm {

        private VerticalLayout root;
        private Panel panel;
        private TextField username;
        private PasswordField password;
        private PasswordField confirmPassword;
        private Button signupButton;


        @Override
        public BaseForm init() {

            root = new VerticalLayout();
            root.setMargin(true);
            root.setHeight("100%");
            panel = new Panel("Signup");
            panel.setSizeUndefined();
            username = new TextField("Username");
            password = new PasswordField("Password");
            confirmPassword = new PasswordField("Confirm Password");
            signupButton = new Button("Signup");
            signupButton.setStyleName(BUTTON_FRIENDLY);
            signupButton.addClickListener(e -> {

                if(!password.getValue().equals(confirmPassword.getValue())) {
                    new Notification("Error", "Passwords do not match", Notification.Type.ERROR_MESSAGE);
                    return;
                }

                registerService.save(username.getValue(), password.getValue());
                UI.getCurrent().getPage().setLocation("/login");

            });
            return this;
        }

        @Override
        public Component layout() {



            FormLayout signupLayout = new FormLayout();
            signupLayout.addComponent(username);
            signupLayout.addComponent(password);
            signupLayout.addComponent(confirmPassword);
            signupLayout.addComponent(signupButton);
            signupLayout.setSizeUndefined();
            signupLayout.setMargin(true);
            panel.setContent(signupLayout);

            root.addComponent(panel);
            root.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
            return root;
        }
    }
}
