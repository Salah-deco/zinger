package fsac.ms3i.zinger.controller;

import fsac.ms3i.zinger.model.Post;
import fsac.ms3i.zinger.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts")
    public ResponseEntity<?> getPosts() {
        List<Post> posts = postRepository.findAll();
        if (posts.size() > 0) {
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("No posts available", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> getPost(@PathVariable String id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            return new ResponseEntity<>(postOptional, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Post not found with id = " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        try {
            post.setCreatedAt(new Date(System.currentTimeMillis()));
            post.setBlocked(false);
            post.setComments(new ArrayList<>());
            post.setReactions(new HashMap<>());
            post.setReports(new ArrayList<>());
            // Validation...

            // Save
            postRepository.save(post);
            return new ResponseEntity<>(post, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody Post post) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post postToUpate = postOptional.get();
            postToUpate.setBody(post.getBody() != null ? post.getBody() : postToUpate.getBody());
            postToUpate.setUpdatedAt(new Date(System.currentTimeMillis()));

            postRepository.save(postToUpate);
            return new ResponseEntity<>(postToUpate, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Post not found with id = " + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        try {
            postRepository.deleteById(id);
            return new ResponseEntity<>("Delete post with id = " + id, HttpStatus.OK);

            // Delete all comments

            // Delete all reactions

            // Delete all reports

            // Delete post from list post in User model


        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
