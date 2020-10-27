package course.java.invoicing.dao;

import course.java.invoicing.model.Product;
import course.java.invoicing.model.User;

public interface UserRepository extends Repository<Long, User> {
    User findByUsername(String username);
}
