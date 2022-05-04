package fsac.ms3i.zinger.controller;

import fsac.ms3i.zinger.Model.Comment;
import fsac.ms3i.zinger.Model.User;
import fsac.ms3i.zinger.repository.CommentRepository;
import fsac.ms3i.zinger.repository.UserRepository;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import  java.util.List;
import java.util.Optional;

@RestController
public class CommentController {
    @Autowired
    private CommentRepository ComRepo;

    @GetMapping("/Comments")
    public ResponseEntity<?> getComments() {
        List<Comment> com = ComRepo.findAll();

        return new ResponseEntity<>(com, com.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/Commente/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable String id ) {
        Optional<Comment> com = ComRepo.findById(id);
        if(com.isPresent()){
            return new ResponseEntity<>(com, HttpStatus.OK);
        }
        else{

        return new ResponseEntity<>(com,HttpStatus.NOT_FOUND );}
    }


        @PostMapping("/Comments")
        public ResponseEntity<?> postComment(@RequestBody Comment comm){
         try {
             comm.setCreatedAt(new Date(System.currentTimeMillis()));
            ComRepo.save(comm);
             return new ResponseEntity<Comment>(comm , HttpStatus.OK );
         }
         catch (Exception e)
         {
             return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
         }




        }
    @PutMapping("/Comment/{id}")
    public ResponseEntity<?> Update(@PathVariable("id") String id,@RequestBody Comment comment ) {

        Optional<Comment> CommeOpt =ComRepo.findById(id);
       if (CommeOpt.isPresent()){
           Comment commSave = CommeOpt.get();
           commSave.setBody(comment.getBody()!=null ? comment.getBody() :commSave.getBody());
           commSave.setUpdateAt(new Date(System.currentTimeMillis()));
           ComRepo.save(commSave);
           return new ResponseEntity<>(commSave, HttpStatus.OK );
       }
       else
           return new ResponseEntity<>("Not found " + id ,HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/comment/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable String id) {
        Optional<Comment> commentOptional = ComRepo.findById(id);
        if (commentOptional.isPresent()) {
            ComRepo.deleteById(id);
            Optional<Comment> deletedComment = ComRepo.findById(id);
            if (!deletedComment.isPresent()) {
                return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>("OK", HttpStatus.OK);
            }
        }
        else {
            return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

}

