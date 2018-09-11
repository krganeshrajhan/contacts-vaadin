package com.example.vaadinexample.ui.login;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;

import java.util.List;

@SpringUI(path = "/menu")
@Title("Menu")
@Theme("valo")
public class MenuUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);

        HorizontalLayout menuLayout = new HorizontalLayout();
        menuLayout.setSpacing(true);

        Label myFirstLabel = new Label("-");
        myFirstLabel.addStyleName("myfirstlabel");

        Label mySecondLabel = new Label("-");
        mySecondLabel.addStyleName("mysecondlabel");

        Label secondMenuLabel = new Label("-");
        secondMenuLabel.addStyleName("secondmenulabel");

        MenuBar.Command myFirstCommand = new MenuBar.Command() {

            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                myFirstLabel.setValue(selectedItem.getText());
                if(selectedItem.hasChildren()){
                    List<MenuBar.MenuItem> items = selectedItem.getChildren();
                    StringBuilder sb = new StringBuilder();
                    for(MenuBar.MenuItem item : items){
                        sb.append(item.getText());
                    }
                    mySecondLabel.setValue(sb.toString());
                }else{
                    mySecondLabel.setValue("-");
                }
                secondMenuLabel.setValue("-");
            }
        };

        MenuBar.Command mySecondCommand = new MenuBar.Command() {

            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                mySecondLabel.setValue(selectedItem.getText());
                MenuBar.MenuItem parent = selectedItem.getParent();
                if(parent!=null){
                    myFirstLabel.setValue(parent.getText());
                }
                secondMenuLabel.setValue("-");
            }
        };

        MenuBar.Command secondMenuCommand = new MenuBar.Command() {

            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                myFirstLabel.setValue("-");
                mySecondLabel.setValue("-");
                secondMenuLabel.setValue("second menu clicked " + selectedItem.getText());
            }
        };

        MenuBar menu = new MenuBar();
        menu.addStyleName("firstmenu");
        MenuBar.MenuItem file = menu.addItem("File", FontAwesome.FOLDER, null);
        MenuBar.MenuItem edit = menu.addItem("Edit", null, null);
        MenuBar.MenuItem help = menu.addItem("Help", FontAwesome.QUESTION_CIRCLE, null);

        MenuBar.MenuItem create = file.addItem("New", FontAwesome.NEWSPAPER_O, myFirstCommand);
        file.addItem("Open", FontAwesome.FOLDER_OPEN, myFirstCommand);
        file.addItem("Close", null, myFirstCommand);

        create.addItem("New Text File", FontAwesome.FILE, mySecondCommand);
        create.addItem("Other", mySecondCommand);

        edit.addItem("Cut", FontAwesome.CUT, myFirstCommand);
        edit.addItem("Copy", FontAwesome.COPY, myFirstCommand);
        edit.addItem("Paste", FontAwesome.PASTE, myFirstCommand);

        help.addItem("Search", FontAwesome.SEARCH, myFirstCommand);
        MenuBar.MenuItem index = help.addItem("Index", myFirstCommand);
        MenuBar.MenuItem level01 = index.addItem("level01", mySecondCommand);
        MenuBar.MenuItem level02 = level01.addItem("level02", mySecondCommand);
        level02.addItem("level03", mySecondCommand);
        help.addSeparator();
        help.addItem("About", myFirstCommand);

        MenuBar menu2 = new MenuBar();
        menu2.addStyleName("secondmenu");
        MenuBar.MenuItem item1 = menu2.addItem("Item1", FontAwesome.ADN, null);
        item1.addItem("X1", FontAwesome.AMBULANCE, secondMenuCommand);
        item1.addItem("X2", FontAwesome.WIFI, secondMenuCommand);
        item1.addItem("X3", FontAwesome.ADJUST, secondMenuCommand);
        menu2.addItem("Item2", FontAwesome._500PX, secondMenuCommand);
        menu2.addItem("Menu3", FontAwesome.QUESTION_CIRCLE, secondMenuCommand);

        menuLayout.addComponent(menu);
        menuLayout.addComponent(menu2);
        layout.addComponent(menuLayout);
        layout.addComponent(myFirstLabel);
        layout.addComponent(mySecondLabel);
        layout.addComponent(secondMenuLabel);
    }
}
