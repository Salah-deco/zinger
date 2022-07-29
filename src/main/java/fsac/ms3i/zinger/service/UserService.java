package fsac.ms3i.zinger.service;

import fsac.ms3i.zinger.exception.UserCollectionException;
import fsac.ms3i.zinger.model.User;

import javax.validation.ConstraintViolationException;
import java.util.List;

public interface UserService {
    public List<User> getUsers();
    public User getUser(String id) throws UserCollectionException;
    public void createUser(User user) throws  ConstraintViolationException, UserCollectionException;
    public void updateUser(String id, User user) throws ConstraintViolationException, UserCollectionException;
    public void blockUser(String id) throws UserCollectionException;
    public Boolean auth(String email, String password) throws UserCollectionException;
}
