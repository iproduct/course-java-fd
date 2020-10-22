package course.java.invoicing.model;

public class Supplier extends Contragent {
    private String iban;
    private String bic;

    public Supplier() {
    }

    public Supplier(Long id, String name, String address, String iban, String bic) {
        super(id, name, address);
        this.iban = iban;
        this.bic = bic;
    }

    public Supplier(Long id, String name, String address, String vatNumber, String iban, String bic) {
        super(id, name, address, vatNumber);
        this.iban = iban;
        this.bic = bic;
    }

    public Supplier(Long id, String name, String address, String vatNumber,
                    String telephone, String iban, String bic) {
        super(id, name, address, vatNumber, telephone);
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
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", iban='").append(iban).append('\'');
        sb.append(", bic='").append(bic).append('\'');
        return sb.toString();
    }
}
