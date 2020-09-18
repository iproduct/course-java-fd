package org.iproduct.invoicing.model;

import org.iproduct.invoicing.dao.Identifiable;

import java.util.Objects;

public class Client extends Contragent {
    private String email;
    private boolean person = false;

    public Client() {
        // super();
    }

    public Client(Long id) {
        super(id);
    }

    public Client(Long id, String name, String address, String email) {
        super(id, name, address);
        this.email = email;
    }

    public Client(Long id, String name, String address, String telephone, String vatNumber, String email) {
        super(id, name, address, telephone, vatNumber);
        this.email = email;
    }

    public Client(Long id, String name, String address, String telephone, String vatNumber, String email, boolean person) {
        super(id, name, address, telephone, vatNumber);
        this.email = email;
        this.person = person;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPerson() {
        return person;
    }

    public void setPerson(boolean person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return new StringBuilder("Client{")
                .append(super.toString())
                .append(", email='").append(email).append('\'')
                .append(", ").append(person? "person" : "company")
                .append('}')
                .toString();
    }
}
