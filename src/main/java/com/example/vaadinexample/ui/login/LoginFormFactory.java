package com.example.vaadinexample.ui.login;

import com.example.vaadinexample.ui.base.BaseForm;
import com.example.vaadinexample.ui.base.BaseFormFactory;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LoginFormFactory implements BaseFormFactory {

    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    private class LoginForm implements BaseForm{

        private VerticalLayout root = new VerticalLayout();
        private Panel panel = new Panel("Login");
        private TextField username = new TextField("Username");
        private PasswordField password = new PasswordField("Password");
        private Button loginButton = new Button("Login");
        private Button signupButton = new Button("Signup");

        public LoginForm init() {

            root.setMargin(true);
            panel.setSizeUndefined();
            loginButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
            signupButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
            return this;
        }

        public com.vaadin.ui.Component layout() {

            FormLayout loginLayout = new FormLayout();
            loginLayout.addComponent(username);
            loginLayout.addComponent(password);
            loginLayout.addComponent(new HorizontalLayout(loginButton, signupButton));
            loginLayout.setSizeUndefined();
            loginLayout.setMargin(true);

            loginButton.addClickListener(e -> {

                try {
                    Authentication auth = new UsernamePasswordAuthenticationToken(username.getValue(), password.getValue());
                    Authentication authentication = daoAuthenticationProvider.authenticate(auth);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    UI.getCurrent().getPage().setLocation("/contact");
                } catch (AuthenticationException ex) {
                    Notification.show("Login Failed: "+ex.getMessage(), Notification.Type.ERROR_MESSAGE);
                }


            });
            signupButton.addClickListener(e -> {

                UI.getCurrent().getPage().setLocation("/signup");
            });

            panel.setContent(loginLayout);
            root.addComponent(panel);
            root.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
            return root;
        }

    }

    @Override
    public com.vaadin.ui.Component createComponent() {
        return new LoginForm().init().layout();
    }
}
