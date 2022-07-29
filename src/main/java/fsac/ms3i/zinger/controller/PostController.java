package fsac.ms3i.zinger.controller;

import fsac.ms3i.zinger.exception.PostCollectionException;
import fsac.ms3i.zinger.model.Post;
import fsac.ms3i.zinger.repository.PostRepository;
import fsac.ms3i.zinger.service.PostServiceImp;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostServiceImp postServiceImp;

    @GetMapping("/posts")
    public ResponseEntity<?> getPosts() {
        List<Post> posts = postServiceImp.getPosts();
        return new ResponseEntity<>(posts, posts.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> getPost(@PathVariable String id) {
        try {
            return new ResponseEntity<>(postServiceImp.getPost(id), HttpStatus.OK);
        } catch (PostCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        try {
            Post createdPost = postServiceImp.createPost(post);
            return new ResponseEntity<>(createdPost, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody Post post) {
        try {
            postServiceImp.updatePost(id, post);
            return new ResponseEntity<>("Update post with id = " + id, HttpStatus.OK);
        } catch (PostCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    // like post
    @PutMapping("/post/like/{id}")
    public ResponseEntity<?> likePost(@PathVariable("id") String id, @RequestParam String userId) {
        System.out.println("PUT method: /post/like/" + id + " with param = userId = " + userId);
        try {
            postServiceImp.likePost(id, userId);
            return new ResponseEntity<>("like successfuly", HttpStatus.OK);
        } catch (PostCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    // remove like
    @PutMapping("/post/removelike/{id}")
    public ResponseEntity<?> removeLike(@PathVariable("id") String id, @RequestParam String userId) {
        System.out.println("PUT method: /post/removelike/" + id + " with param = userId = " + userId);
        try {
            postServiceImp.removeLike(id, userId);
            return new ResponseEntity<>("remove like successfuly", HttpStatus.OK);
        } catch (PostCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        try {
            postServiceImp.deletePost(id);
            return new ResponseEntity<>("Delete post with id = " + id, HttpStatus.OK);
        } catch (PostCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // pagination
    @GetMapping("/page")
    public ResponseEntity<?> getPage(Pageable page) {
        Page<Post> posts = postRepository.findAll(page);
        return new ResponseEntity<>(posts, posts.isEmpty() == false ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
