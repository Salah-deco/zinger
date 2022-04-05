package fsac.ms3i.zinger.controller;

import fsac.ms3i.zinger.exception.UserCollectionException;
import fsac.ms3i.zinger.model.User;
import fsac.ms3i.zinger.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        List<User> users = userServiceImp.getUsers();
        return new ResponseEntity<>(users, users.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        try {
            return new ResponseEntity<>(userServiceImp.getUser(id), HttpStatus.OK);
        } catch (UserCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            userServiceImp.createUser(user);
            return new ResponseEntity<>("User created", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        try {
            userServiceImp.updateUser(id, user);
            return new ResponseEntity<>("User Update!", HttpStatus.OK);
        } catch (UserCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> blockUser(@PathVariable String id) {
        try {
            userServiceImp.blockUser(id);
            return new ResponseEntity<>("User Blocked!!", HttpStatus.OK);
        } catch (UserCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody User user) {
        try {
            boolean exist = userServiceImp.auth(user.getEmail(), user.getPassword());
            return new ResponseEntity<>("auth succes!! " + ((exist == true) ? "true" : "false"), HttpStatus.OK);
        } catch (UserCollectionException e) {
            return new ResponseEntity<>("auth failed: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
