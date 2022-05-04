package fsac.ms3i.zinger.controller;

import fsac.ms3i.zinger.Model.Comment;
import fsac.ms3i.zinger.Model.User;
import fsac.ms3i.zinger.controller.UserController;
import fsac.ms3i.zinger.repository.CommentRepository;
import fsac.ms3i.zinger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import  java.util.List;
import java.util.Optional;
@RestController
public class AfficheController {

    @Autowired
    private CommentRepository ComRepo;
    private UserRepository UserRepo;
    @GetMapping("/CommentPost/{id}")
    public ResponseEntity<?> getCommentbyid(@PathVariable("id") String id) {
        List<Comment> com = ComRepo.findBypostid(id);// sélectionner la list des commentaires d'une poste



        return new ResponseEntity<>(com,HttpStatus.OK);
    }
/* Error server 500
    @GetMapping("/CommentPost2/{id}")
    public ResponseEntity<?> getPostUser(@PathVariable("id") String id) {
        List<Comment> com = ComRepo.findBypostid(id);
        List <User> Us=UserRepo.findAll();
        List <User> Users=null;
        for(int i=0; i< com.size();i++){
            for(int j=0;j<Us.size();j++){
                if(com.get(i).getUserId()==Us.get(j).getId())
                {
                   Users.add(Us.get(j));
                }
            }

        }



        return new ResponseEntity<>(Users,HttpStatus.OK);
    }*

 */

}

