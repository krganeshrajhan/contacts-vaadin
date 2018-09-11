package com.example.vaadinexample.ui.base;

import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Component;

public interface BaseFormFactory {

    public com.vaadin.ui.Component createComponent();
}
