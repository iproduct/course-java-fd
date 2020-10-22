package course.java.invoicing.model;

public class Client extends Contragent {
    private String email;
    boolean person = false;

    public Client() {
    }

    public Client(Long id, String name, String address) {
        super(id, name, address);
    }

    public Client(Long id, String name, String address, String email) {
        super(id, name, address);
        this.email = email;
    }

    public Client(Long id, String name, String address, String email, boolean person) {
        super(id, name, address);
        this.email = email;
        this.person = person;
    }

    public Client(Long id, String name, String address, String vatNumber, String email, boolean person) {
        super(id, name, address, vatNumber);
        this.email = email;
        this.person = person;
    }

    public Client(Long id, String name, String address, String vatNumber, String telephone, String email, boolean person) {
        super(id, name, address, vatNumber, telephone);
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
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("email='").append(email).append('\'');
        sb.append(", person=").append(person);
        return sb.toString();
    }
}
