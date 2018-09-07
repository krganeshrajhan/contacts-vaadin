package com.example.vaadinexample.ui.login;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = "/signup")
@Theme("valo")
@Title("Signup")
public class SignupUI extends UI {

    @Autowired
    private SignupFormFactory signupFormFactory;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(signupFormFactory.createComponent());
    }
}
