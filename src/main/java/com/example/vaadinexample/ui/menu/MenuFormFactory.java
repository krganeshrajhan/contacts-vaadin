package com.example.vaadinexample.ui.menu;

import com.example.vaadinexample.ui.MainUI;
import com.example.vaadinexample.ui.base.BaseFormFactory;
import com.example.vaadinexample.ui.login.MenuUI;
import com.vaadin.ui.UI;
import org.springframework.stereotype.Component;

@Component
public class MenuFormFactory implements BaseFormFactory {


    @Override
    public com.vaadin.ui.Component createComponent() {
        return new MenuForm().init().layout();
    }

    public com.vaadin.ui.Component createComponent(MainUI ui) {
        return new MenuForm(ui).init().layout();
    }
}
