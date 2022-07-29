package fsac.ms3i.zinger.controller;

import fsac.ms3i.zinger.exception.UserCollectionException;
import fsac.ms3i.zinger.model.User;
import fsac.ms3i.zinger.repository.UserRepository;
import fsac.ms3i.zinger.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private UserRepository userRepository;

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
            String s_id = UUID.randomUUID().toString();
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setBearerAuth(s_id);
            System.out.println(s_id);
            Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
            if (exist)
                return ResponseEntity.ok().headers(responseHeaders).body(optionalUser.get());
            else
                return null;
//            return ResponseEntity<>(false, HttpStatus.NOT_FOUND);
            // HttpSession session = RequestBo
            // return new ResponseEntity<>("auth succes!! " + ((exist == true) ? "true" : "false"), responseHeaders, HttpStatus.OK);
        } catch (UserCollectionException e) {
            return new ResponseEntity<>("auth failed: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/user/verify/{email}/{password}")
//    public ResponseEntity<?> verifyUser(@PathVariable String email, @PathVariable String password) {
//        try {
//            User user = userServiceImp.auth(email, password);
//            if (user == null) {
//                return new ResponseEntity<>("ZNG-201", HttpStatus.FORBIDDEN);
//            } else {
//                return new ResponseEntity<>(user, HttpStatus.OK);
//            }
//        } catch (UserCollectionException exception) {
//            System.out.println(exception.getMessage());
//            return new ResponseEntity<>("ZNG-201", HttpStatus.NOT_FOUND);
//        }
//    }

//    @GetMapping("/user/verify/{email}/{password}")
//    public ResponseEntity<?> verifyUser(@PathVariable String email, @PathVariable String password) {
//        Optional<User> userOptional = userRepository.findByEmail(email);
//        if (userOptional.isPresent()) {
//            if (userOptional.get().getPassword().equals(password)) {
//                return new ResponseEntity<>(userOptional, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("ZNG-201", HttpStatus.NOT_FOUND);
//            }
//        } else {
//            return new ResponseEntity<>("ZNG-201", HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/user/verifyEmail/{email}")
    public ResponseEntity<?> verifyEmail(@PathVariable String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return new ResponseEntity<>("ZNG-101", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ZNG-201", HttpStatus.NOT_FOUND);
        }
    }
}
