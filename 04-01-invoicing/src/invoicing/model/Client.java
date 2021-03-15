package invoicing.model;

import java.util.Date;

public class Client extends Contragent {
    private String email;

    public Client() {
    }

    public Client(Long id) {
        super(id);
    }

    public Client(String name, String address, String idNumber, String countryCode, String email) {
        super(name, address, idNumber, countryCode);
        this.email = email;
    }

    public Client(String name, String address, String idNumber, String countryCode, String phone, String email) {
        super(name, address, idNumber, countryCode, phone);
        this.email = email;
    }

    public Client(String name, String address, String idNumber, String email) {
        super(name, address, idNumber);
        this.email = email;
    }

    public Client(Long id, String name, String address, String idNumber, String countryCode, String phone,
                  String email, boolean corporate, Date created, Date updated, Long createdById, Long updatedById) {
        super(id, name, address, idNumber, countryCode, phone, corporate, created, updated, createdById, updatedById);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append(super.toString());
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
