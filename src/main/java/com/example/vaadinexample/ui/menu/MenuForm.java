package com.example.vaadinexample.ui.menu;

import com.example.vaadinexample.ui.MainUI;
import com.example.vaadinexample.ui.base.BaseForm;
import com.example.vaadinexample.ui.login.MenuUI;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class MenuForm implements BaseForm {

    private HorizontalLayout menuLayout;
    private MenuBar menu;

    private MenuBar.MenuItem contactMenu;
    private MenuBar.MenuItem help;
    private MenuBar.MenuItem index;
    private MenuBar.MenuItem level01;
    private MenuBar.MenuItem level02;
    private MenuBar.Command setPageCommand;
    private MainUI ui;
    private Button logout;

    public MenuForm() {

    }

    public MenuForm(MainUI ui) {
        this.ui = ui;
    }


    @Override
    public MenuForm init() {
        menuLayout = new HorizontalLayout();
        menuLayout.setSpacing(true);
        menuLayout.setSizeFull();
        menu = new MenuBar();
        setPageCommand = selectedItem -> {

            ui.setPageLayout(new HorizontalLayout());

        };

        contactMenu = menu.addItem("Contacts", VaadinIcons.BOOK, null);
        help = menu.addItem("Help", FontAwesome.QUESTION_CIRCLE, null);
        /*help.addItem("Search", FontAwesome.SEARCH, null);
        index = help.addItem("Index", null);
        level01 = index.addItem("level01", null);
        level02 = level01.addItem("level02", null);
        level02.addItem("level03", null);
        help.addSeparator();*/
        contactMenu.addItem("Add", setPageCommand);
        help.addItem("About", setPageCommand);
        logout = new Button("Logout");
        logout.setIcon(VaadinIcons.POWER_OFF);
        logout.setStyleName(ValoTheme.BUTTON_PRIMARY);
        logout.addClickListener(e -> logoutCall());
        return this;
    }

    private void logoutCall() {
        ui.getSession().close();
        ui.getNavigator().navigateTo("/login");
    }

    @Override
    public Component layout() {
        menuLayout.addComponent(menu);
        menuLayout.addComponent(logout);
        menuLayout.setComponentAlignment(logout, Alignment.MIDDLE_RIGHT);
        return menuLayout;
    }
}
