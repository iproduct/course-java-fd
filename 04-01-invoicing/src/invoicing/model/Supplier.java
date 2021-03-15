package invoicing.model;

import java.util.Date;

public class Supplier extends Contragent {
    private String iban;
    private String bic;

    public Supplier() {
    }

    public Supplier(Long id) {
        super(id);
    }

    public Supplier(String name, String address, String idNumber, String countryCode, String iban, String bic) {
        super(name, address, idNumber, countryCode);
        this.iban = iban;
        this.bic = bic;
    }

    public Supplier(String name, String address, String idNumber, String countryCode, String phone, String iban, String bic) {
        super(name, address, idNumber, countryCode, phone);
        this.iban = iban;
        this.bic = bic;
    }

    public Supplier(String name, String address, String idNumber, String iban, String bic) {
        super(name, address, idNumber);
        this.iban = iban;
        this.bic = bic;
    }

    public Supplier(Long id, String name, String address, String idNumber, String countryCode, String phone, boolean corporate,
                    String iban, String bic, Date created, Date updated, Long createdById, Long updatedById) {
        super(id, name, address, idNumber, countryCode, phone, corporate, created, updated, createdById, updatedById);
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
        final StringBuilder sb = new StringBuilder("Supplier{");
        sb.append(super.toString());
        sb.append(", iban='").append(iban).append('\'');
        sb.append(", bic='").append(bic).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
