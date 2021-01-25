package invoicing.model;

import invoicing.dao.Identifiable;

import java.util.Objects;

public class Contragent implements Identifiable<Long>, Comparable<Contragent> {
   private Long id;
   private final String name;
   private String address;
   private String idNumber;
   private String phone;

    public Contragent() {
        this.name = "Anonimous";
    }

    public Contragent(Long id) {
        this.name = "Anonimous";
        this.id = id;
    }

    public Contragent(String name, String address, String idNumber) {
        this.name = name;
        this.address = address;
        this.idNumber = idNumber;
    }

    public Contragent(String name, String address, String idNumber, String phone) {
        this.name = name;
        this.address = address;
        this.idNumber = idNumber;
        this.phone = phone;
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

//    public void setName(String name) {
//        this.name = name;
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contragent)) return false;
        Contragent that = (Contragent) o;
        return Objects.equals(id, that.id);
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
        sb.append(", idNumber='").append(idNumber).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        return sb.toString();
    }

    @Override
    public int compareTo(Contragent o) {
        return getId().compareTo(o.getId());
    }

    public String format(){
        return String.format("| %5d | %-20.20s | %-20.20s | %10.10s | %-15.15s | ",
                id, name, address, idNumber, phone);
    }
}
