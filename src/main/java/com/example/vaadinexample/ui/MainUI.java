package com.example.vaadinexample.ui;

import com.example.vaadinexample.ui.menu.MenuFormFactory;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = "/")
@Title("Contacts")
@Theme("valo")
public class MainUI extends UI {

    private VerticalLayout main;
    private HorizontalLayout menuLayout;
    @Setter
    private HorizontalLayout pageLayout;

    @Autowired
    private MenuFormFactory menuFormFactory;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        main = new VerticalLayout();
        menuLayout = (HorizontalLayout) menuFormFactory.createComponent(this);
        pageLayout = new HorizontalLayout();
        main.addComponent(menuLayout);
        main.addComponent(pageLayout);
        setContent(main);
    }
}
