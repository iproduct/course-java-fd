package invoicing.dao;

import invoicing.model.User;

public interface UserRepository extends Repository<Long, User>{
    User getUserByUsername(String username);
}
