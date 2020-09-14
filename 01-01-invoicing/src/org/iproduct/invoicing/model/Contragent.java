package org.iproduct.invoicing.model;

import org.iproduct.invoicing.dao.Identifiable;

import java.util.Objects;

public class Contragent implements Identifiable<Long> {
    private Long id;
    private String name;
    private String address;
    private String telephone;
    private String vatNumber;

    public Contragent() {
    }

    public Contragent(Long id) {
        this.id = id;
    }

    public Contragent(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Contragent(Long id, String name, String address, String telephone, String vatNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.vatNumber = vatNumber;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contragent)) return false;
        Contragent that = (Contragent) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", telephone='").append(telephone).append('\'');
        sb.append(", vatNumber='").append(vatNumber).append('\'');
        return sb.toString();
    }
}
