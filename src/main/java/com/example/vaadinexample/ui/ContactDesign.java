package com.example.vaadinexample.ui;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;

/**
 * !! DO NOT EDIT THIS FILE !!
 * <p>
 * This class is generated by Vaadin Designer and will be overwritten.
 * <p>
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class ContactDesign extends FormLayout {
    protected TextField firstName;
    protected TextField lastName;
    protected Button save;
    protected Button cancel;
    protected Button delete;

    public ContactDesign() {
        Design.read(this);
    }
}