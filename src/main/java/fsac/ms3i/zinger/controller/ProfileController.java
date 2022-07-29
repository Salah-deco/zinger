package fsac.ms3i.zinger.controller;

import fsac.ms3i.zinger.exception.PostCollectionException;
import fsac.ms3i.zinger.model.Comment;
import fsac.ms3i.zinger.model.Post;
import fsac.ms3i.zinger.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/postsOfUser/{userId}")
    public ResponseEntity<?> getPostOfUser(@PathVariable String userId) {
        List<Post> posts = profileService.getPostsOfUser(userId);
        return new ResponseEntity<>(posts, (posts.size() > 0) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/commentsOfPost/{postId}")
    public ResponseEntity<?> getCommentOfPost(@PathVariable String postId) {
        List<Comment> comments = profileService.getCommentsOfPost(postId);
        return new ResponseEntity<>(comments, (comments.size() > 0) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
