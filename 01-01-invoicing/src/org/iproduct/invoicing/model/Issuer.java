package org.iproduct.invoicing.model;

import org.iproduct.invoicing.dao.Identifiable;

import java.util.Objects;

public class Issuer implements Identifiable<Long> {
    Long id;
    String name;
    String address;
    String iban;
    String bic;
    String telephone;
    String vatNumber;

    public Issuer() {
    }

    public Issuer(Long id, String name, String address, String iban, String bic) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.iban = iban;
        this.bic = bic;
    }

    public Issuer(Long id, String name, String address, String iban, String bic,
                  String telephone, String vatNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.iban = iban;
        this.bic = bic;
        this.telephone = telephone;
        this.vatNumber = vatNumber;
    }

    public Long getId() {
        return id;
    }

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

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
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
        if (!(o instanceof Issuer)) return false;
        Issuer issuer = (Issuer) o;
        return id.equals(issuer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Issuer{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", iban='").append(iban).append('\'');
        sb.append(", bic='").append(bic).append('\'');
        sb.append(", telephone='").append(telephone).append('\'');
        sb.append(", vatNumber='").append(vatNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
