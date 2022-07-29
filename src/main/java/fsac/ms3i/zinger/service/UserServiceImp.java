package fsac.ms3i.zinger.service;

import fsac.ms3i.zinger.exception.UserCollectionException;
import fsac.ms3i.zinger.model.User;
import fsac.ms3i.zinger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        if (users.size() > 0) {
            return users;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public User getUser(String id) throws UserCollectionException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserCollectionException(UserCollectionException.NotFoundException(id));
        }
    }

    @Override
    public void createUser(User user) throws ConstraintViolationException, UserCollectionException {
        user.setCreatedAt(new Date(System.currentTimeMillis()));
        user.setBlocked(false);
        user.setIdsPosts(new ArrayList<>());
        user.setIdsComments(new ArrayList<>());

        // Encrypt password
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        // save
        userRepository.save(user);
    }

    @Override
    public void updateUser(String id, User user) throws ConstraintViolationException, UserCollectionException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User userToUpdate = optionalUser.get();

            userToUpdate.setFirst_name(user.getFirst_name() != null ? user.getFirst_name() : userToUpdate.getFirst_name());
            userToUpdate.setLast_name(user.getLast_name() != null ? user.getLast_name() : userToUpdate.getLast_name());
            userToUpdate.setEmail(user.getEmail() != null ? user.getEmail() : userToUpdate.getEmail());
            userToUpdate.setBio(user.getBio() != null ? user.getBio() : userToUpdate.getBio());
            userToUpdate.setImage(user.getImage() != null ? user.getImage() : userToUpdate.getImage());

            userRepository.save(userToUpdate);
        } else {
            throw new UserCollectionException(UserCollectionException.NotFoundException(id));
        }
    }

    @Override
    public void blockUser(String id) throws UserCollectionException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User userToBlock = optionalUser.get();

            userToBlock.setBlocked(true);
            // masque son posts, comments
            // ...

            userRepository.save(userToBlock);
        } else {
            throw new UserCollectionException(UserCollectionException.NotFoundException(id));
        }
    }

    public Boolean auth(String email, String password) throws UserCollectionException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (this.passwordEncoder.matches(password, user.getPassword()))
                return true;
                // return optionalUser.get();
            else
                return false;
        } else {
            throw new UserCollectionException(UserCollectionException.NotFoundException(email));
        }
    }
}
