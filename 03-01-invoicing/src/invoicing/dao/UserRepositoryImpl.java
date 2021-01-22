package invoicing.dao;

import invoicing.model.Product;
import invoicing.model.User;

import java.util.List;

public class UserRepositoryImpl extends RepositoryMemoryImpl<Long, User> implements UserRepository {
    public UserRepositoryImpl(KeyGenerator<Long> keyGenerator) {
        super(keyGenerator);
    }

    @Override
    public User getUserByUsername(String username) {
        List<User> users = findAll();
        for(User u: users){
            if(u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }
}
