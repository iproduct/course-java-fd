package course.java.invoicing.service;

import course.java.invoicing.dao.UserRepository;
import course.java.invoicing.exception.NonexistingEntityException;
import course.java.invoicing.model.User;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface UserService {
    Collection<User> getAllUsers();
    List<User> getUsersSorted(Comparator<User> comparator);
    User getUserById(Long id) throws NonexistingEntityException;
    User getUserByUsername(String username) throws NonexistingEntityException;
    User addUser(User user);
    User updateUser(User user) throws NonexistingEntityException;
    User deleteUser(Long userId) throws NonexistingEntityException;
    long getCount();
    UserRepository getUserRepository();
    void setUserRepository(UserRepository repo);
}
