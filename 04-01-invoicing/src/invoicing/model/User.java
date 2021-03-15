package invoicing.model;


import java.util.Date;
import java.util.Objects;

public class User extends AbstractEntity<Long, User> {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Role role = Role.USER;

    public User() {
    }

    public User(Long id) {
        super(id);
    }

    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String lastName, String username, String password,
                Date created, Date updated, Long createdById, Long updatedById) {
        super(null, created, updated, createdById, updatedById);
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String lastName, String username, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String firstName, String lastName, String username, String password, Role role,
                Date created, Date updated, Long createdById, Long updatedById) {
        super(null, created, updated, createdById, updatedById);
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(getId());
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", role=").append(role);
        sb.append(", created=").append(getCreated());
        sb.append(", updated=").append(getUpdated());
        sb.append(", createdById=").append(getCreatedById());
        sb.append(", updatedById=").append(getUpdatedById());
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(User other) {
        return getId().compareTo(other.getId());
    }
}
