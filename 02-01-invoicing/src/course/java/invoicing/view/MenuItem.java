package course.java.invoicing.view;

import course.java.invoicing.model.Role;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum MenuItem {
    ADD_PRODUCT("Add Product"),
    PRINT_PRODUCTS("Print All Products"),
    ADD_CLIENT("Add Client"),
    ADD_SUPPLIER("Add Supplier"),
    PRINT_CLIENTS("Print All Clients"),
    PRINT_SUPPLIERS("Print All Suppliers"),
    WRITE_TO_FILE("Write to File"),
    READ_FROM_FILE("Read from File"),
    EXIT_SUBMENU("Exit Menu"),
    EXIT("Exit"),
    ADD_USER("Add User", new HashSet<>(Arrays.asList(Role.ADMIN))),
    PRINT_USERS("List All Users", new HashSet<>(Arrays.asList(Role.ADMIN)));
    private String label;
    private Set<Role> rolesAlowed = new HashSet<>(Arrays.asList(Role.USER, Role.ADMIN));

    MenuItem(String label) {
        this.label = label;
    }

    MenuItem(String label, Set<Role> rolesAlowed) {
        this.label = label;
        this.rolesAlowed = rolesAlowed;
    }

    public String getLabel() {
        return label;
    }

    public Set<Role> getRolesAlowed() {
        return rolesAlowed;
    }
}
