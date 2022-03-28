package fsac.ms3i.zinger.controller;

import fsac.ms3i.zinger.exception.CommentCollectionException;
import fsac.ms3i.zinger.model.Comment;
import fsac.ms3i.zinger.service.CommentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentServiceImp commentServiceImp;

    @GetMapping("/comments")
    public ResponseEntity<?> getComments() {
        List<Comment> comments = commentServiceImp.getComments();
        return new ResponseEntity<>(comments, comments.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<?> getComment(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(commentServiceImp.getComment(id), HttpStatus.OK);
        } catch (CommentCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/comment")
    public ResponseEntity<?> createPost(@RequestBody Comment comment) {
        try {
            Comment createdComment = commentServiceImp.createComment(comment);
            return new ResponseEntity<>(createdComment, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        try {
            commentServiceImp.deleteComment(id);
            return new ResponseEntity<>("Delete comment with id = " + id, HttpStatus.OK);
        } catch (CommentCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

