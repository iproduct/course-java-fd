package invoicing.model;

public class Client extends Contragent{
    private String email;


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


    public Client(String name, String address, String idNumber, String phone, String email, boolean corporate) {
        super(name, address, idNumber, phone, corporate);
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
