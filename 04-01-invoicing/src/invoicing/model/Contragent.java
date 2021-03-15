package invoicing.model;

import java.util.Date;

public abstract class Contragent extends AbstractEntity<Long, Contragent> {
    private String name;
    private String address;
    private String idNumber;
    private String countryCode; //supply country code if legal entity is VAT registered
    private String phone;
    private boolean corporate = true;

    public Contragent() {
    }

    public Contragent(Long id) {
        super(id);
    }

    public Contragent(String name, String address, String idNumber, String countryCode) {
        this.name = name;
        this.address = address;
        this.idNumber = idNumber;
        this.countryCode = countryCode;
    }

    public Contragent(String name, String address, String idNumber, String countryCode, String phone) {
        this.name = name;
        this.address = address;
        this.idNumber = idNumber;
        this.countryCode = countryCode;
        this.phone = phone;
    }

    public Contragent(String name, String address, String idNumber) { // phisical persons -> corporate false
        this.name = name;
        this.address = address;
        this.idNumber = idNumber;
        this.corporate = false;
    }

    public Contragent(Long id, String name, String address, String idNumber,
                      String countryCode, String phone, boolean corporate,
                      Date created, Date updated, Long createdById, Long updatedById) {
        super(id, created, updated, createdById, updatedById);
        this.name = name;
        this.address = address;
        this.idNumber = idNumber;
        this.countryCode = countryCode;
        this.phone = phone;
        this.corporate = corporate;
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

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isCorporate() {
        return corporate;
    }

    public void setCorporate(boolean corporate) {
        this.corporate = corporate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("id=").append(getId());
        sb.append(", name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", idNumber='").append(idNumber).append('\'');
        sb.append(", countryCode='").append(countryCode).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", corporate=").append(corporate);
        sb.append(", created=").append(getCreated());
        sb.append(", updated=").append(getUpdated());
        sb.append(", createdById=").append(getCreatedById());
        sb.append(", updatedById=").append(getUpdatedById());
        return sb.toString();
    }
}
