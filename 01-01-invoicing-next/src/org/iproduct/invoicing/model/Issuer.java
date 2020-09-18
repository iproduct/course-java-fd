package org.iproduct.invoicing.model;

import org.iproduct.invoicing.dao.Identifiable;

import java.util.Objects;

public class Issuer extends Contragent {
    String iban;
    String bic;

    public Issuer() {
    }

    public Issuer(Long id) {
        super(id);
    }

    public Issuer(Long id, String name, String address, String iban, String bic) {
        super(id, name, address);
        this.iban = iban;
        this.bic = bic;
    }

    public Issuer(Long id, String name, String address, String iban, String bic,
                  String telephone, String vatNumber) {
        super(id, name, address, telephone, vatNumber);
        this.iban = iban;
        this.bic = bic;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Issuer{");
        sb.append(super.toString());
        sb.append(", iban='").append(iban).append('\'');
        sb.append(", bic='").append(bic).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
