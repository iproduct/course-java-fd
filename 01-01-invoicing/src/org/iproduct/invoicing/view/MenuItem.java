package org.iproduct.invoicing.view;

import org.iproduct.invoicing.view.commands.AddProductCommand;
import org.iproduct.invoicing.view.commands.Command;

public enum MenuItem {
    ADD_PRODUCT("Add Product"),
    PRINT_PRODUCTS("Print All Products"),
    WRITE_TO_FILE("Write to File"),
    READ_FROM_FILE("Read from File"),
    EXIT_SUBMENU("Exit Menu"),
    EXIT("Exit");

    private String label;
    private Command command;
    private MenuItem(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
}
