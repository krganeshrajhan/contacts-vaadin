package com.example.vaadinexample.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

@SpringUI(path = "/login")
@Title("Login")
@Theme("valo")
public class LoginUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {

    }
}
