package fsac.ms3i.zinger.controller;
import fsac.ms3i.zinger.Model.Comment;
import fsac.ms3i.zinger.Model.Post;
import fsac.ms3i.zinger.Model.User;
import fsac.ms3i.zinger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fsac.ms3i.zinger.repository.CommentRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@RestController
public class UserController {
    @Autowired
    private UserRepository UserRepo;
    private CommentRepository ComRepo;
    @GetMapping("/Users")
    public ResponseEntity<?> getUsers() {
        List<User> user = UserRepo.findAll();

        return new ResponseEntity<>(user, user.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    @GetMapping("/Userby/{id}")
    public ResponseEntity<?> getUserbyId(@PathVariable String id ) {
        Optional<User> user = UserRepo.findById(id);
        if(user.isPresent()){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else{

            return new ResponseEntity<>(user,HttpStatus.NOT_FOUND );}
    }
    @PostMapping("/User")
    public ResponseEntity<?> postUser(@RequestBody User user){
        try {
            user.setCreatedAt(new Date(System.currentTimeMillis()));
            UserRepo.save(user);
            return new ResponseEntity<User>(user, HttpStatus.OK );
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }
    @PutMapping("/User/{id}")
    public ResponseEntity<?> Update(@PathVariable("id") String id, @RequestBody User user ) {

        Optional<User> UserOpt =UserRepo.findById(id);
        if (UserOpt.isPresent()){
            User UserSave = UserOpt.get();
            UserSave.setEmail(user.getEmail()!=null ? user.getEmail() :UserSave.getEmail());
            UserSave.setBio(user.getBio()!=null ? user.getBio() :UserSave.getBio());
            UserRepo.save(UserSave);
            return new ResponseEntity<>(UserSave, HttpStatus.OK );
        }
        else
            return new ResponseEntity<>("Not found " + id ,HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/User/Delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable String id) {
        Optional<User> userOpt = UserRepo.findById(id);
        if (userOpt.isPresent()) {
            UserRepo.deleteById(id);
            Optional<User> userDelt = UserRepo.findById(id);
            if (!userDelt.isPresent()) {
                return new ResponseEntity<>( "NOT FOUND", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>("OK", HttpStatus.OK);
            }
        }
        else {
            return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }
}
