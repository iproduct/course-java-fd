package course.java.invoicing.dao;

import course.java.invoicing.model.Product;
import course.java.invoicing.model.User;

public class UserRepositoryMock extends MockRepository<Long, User> implements UserRepository {
    public UserRepositoryMock(KeyGenerator<Long> keyGenerator) {
        super(keyGenerator);
    }

    @Override
    public User findByUsername(String username) {
//        return findAll().stream().filter((User user) -> user.getUsername().equals(username))
//                .findFirst().orElse(null);
        for(User user : findAll()) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
