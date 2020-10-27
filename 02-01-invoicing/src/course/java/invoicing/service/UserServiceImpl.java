package course.java.invoicing.service;

import course.java.invoicing.dao.UserRepository;
import course.java.invoicing.exception.NonexistingEntityException;
import course.java.invoicing.model.User;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public UserRepository getUserRepository() {
        return userRepo;
    }

    public void setUserRepository(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public List<User> getUsersSorted(Comparator<User> comparator) {
        return userRepo.findAllSorted(comparator);
    }

    @Override
    public User getUserById(Long id) throws NonexistingEntityException {
        User found = userRepo.findById(id);
        if(found == null) {
            throw new NonexistingEntityException(String.format("User with ID:%d does not exist", id));
        }
        return found;
    }

    @Override
    public User getUserByUsername(String username) throws NonexistingEntityException {
        return userRepo.findByUsername(username);
    }

    @Override
    public User addUser(User user) {
        return userRepo.create(user);
    }

    @Override
    public User updateUser(User user) throws NonexistingEntityException {
        User updated = userRepo.update(user);
        if(updated == null) {
            throw new NonexistingEntityException(
                String.format("User '%d: %s' does not exist", user.getId(), user.getName()));
        }
        return updated;
    }

    @Override
    public User deleteUser(Long id) throws NonexistingEntityException {
        User deleted = userRepo.deleteById(id);
        if(deleted == null) {
            throw new NonexistingEntityException(
                    String.format("User with ID:%d does not exist", id));
        }
        return deleted;
    }

    @Override
    public long getCount() {
        return userRepo.count();
    }
}
