package fsac.ms3i.zinger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fsac.ms3i.zinger.exception.CommentCollectionException;
import fsac.ms3i.zinger.exception.PostCollectionException;
import fsac.ms3i.zinger.repository.PostRepository;
import fsac.ms3i.zinger.service.HomeServiceImp;
@RestController
public class Home {

    @Autowired
    private HomeServiceImp HomeServiceImp;
    
    @GetMapping("/postOfUser/{userId}")
    public ResponseEntity<?> getPostOfUser(@PathVariable String userId) throws PostCollectionException {
        return new ResponseEntity<>(HomeServiceImp.getPostOfUser(userId), HttpStatus.OK);
    }
    
    @GetMapping("/commentOfPost/{postId}")
    public ResponseEntity<?> getCommentOfPost(@PathVariable String postId) throws PostCollectionException, CommentCollectionException {
        return new ResponseEntity<>(HomeServiceImp.getCommentOfPost(postId), HttpStatus.OK);
    }
}
