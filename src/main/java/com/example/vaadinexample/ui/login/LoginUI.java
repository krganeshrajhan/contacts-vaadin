package com.example.vaadinexample.ui.login;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = "/login")
@Title("Login")
@Theme("valo")
public class LoginUI extends UI {

    @Autowired
    private LoginFormFactory loginFormFactory;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(loginFormFactory.createComponent());
    }
}
