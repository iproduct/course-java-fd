package invoicing.model;

public class Client extends Contragent{
    private String email;
    private boolean corporation = true;

    public Client() {
    }

    public Client(Long id) {
        super(id);
    }

    public Client(String name, String address, String idNumber, String email) {
        super(name, address, idNumber);
        this.email = email;
    }

    public Client(String name, String address, String idNumber, String phone, String email) {
        super(name, address, idNumber, phone);
        this.email = email;
    }

    public Client(String name, String address, String idNumber, String email, boolean corporation) {
        super(name, address, idNumber);
        this.email = email;
        this.corporation = corporation;
    }

    public Client(String name, String address, String idNumber, String phone, String email, boolean corporation) {
        super(name, address, idNumber, phone);
        this.email = email;
        this.corporation = corporation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isCorporation() {
        return corporation;
    }

    public void setCorporation(boolean corporation) {
        this.corporation = corporation;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append(super.toString());
        sb.append(", email='").append(email).append('\'');
        sb.append(", corporation=").append(corporation);
        sb.append('}');
        return sb.toString();
    }
}
