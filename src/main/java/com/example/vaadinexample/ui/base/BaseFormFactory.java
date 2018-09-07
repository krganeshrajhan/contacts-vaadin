package com.example.vaadinexample.ui.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Component;

public interface BaseFormFactory {

    public com.vaadin.ui.Component createComponent();
}
