package invoicing.model;

public class Person extends Client {
    public Person() {
        setCorporation(false);
    }

    public Person(Long id) {
        super(id);
        setCorporation(false);
    }

    public Person(String name, String address, String idNumber, String email) {
        super(name, address, idNumber, email);
        setCorporation(false);
    }

    public Person(String name, String address, String idNumber, String phone, String email) {
        super(name, address, idNumber, phone, email, false);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}
