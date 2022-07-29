package fsac.ms3i.zinger.controller;

import fsac.ms3i.zinger.exception.UserCollectionException;
import fsac.ms3i.zinger.model.HomePage;
import fsac.ms3i.zinger.model.HomePost;
import fsac.ms3i.zinger.model.Post;
import fsac.ms3i.zinger.model.User;
import fsac.ms3i.zinger.service.PostService;
import fsac.ms3i.zinger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public ResponseEntity<?> home(Pageable page) throws UserCollectionException {

        // verified the S_ID
//        System.out.println(S_ID);

        // User(userId, first_name, last_name, image) post(postId, body, number of likes, number of comments), for admin number of spam

        Page<Post> posts = postService.getPosts(page);

        // initialize Home page
        HomePage homePage = new HomePage();
        // more information about Page
        homePage.setEmpty(posts.isEmpty());
        homePage.setFirst(posts.isFirst());
        homePage.setLast(posts.isLast());
        homePage.setSize(posts.getSize());
        homePage.setNumber(posts.getNumber());
        homePage.setTotalElements(posts.getTotalElements());
        homePage.setTotalPages(posts.getTotalPages());

        if (posts == null || posts.isEmpty()) {
            return new ResponseEntity<>(homePage, HttpStatus.NOT_FOUND);
        }else {
            List<Post> listOfPosts = posts.getContent();

            for (Post post: listOfPosts) {
                User user = userService.getUser(post.getUserId());
//              HomePost(String userId, String first_name, String last_name, String profile_image, String postId, String body, String type, String url, Date createdAt, int number_likes, int number_comments) {
                HomePost homePost = new HomePost(user.getId(),
                            user.getFirst_name(),
                            user.getLast_name(),
                            user.getImage(),
                            post.getId(),
                            post.getBody(),
                            post.getType(),
                            post.getUrl(),
                            post.getCreatedAt(),
                            (post.getLikes() == null) ? 0 : post.getLikes().size(),
                            (post.getComments() == null ) ? 0 : post.getComments().size()
                            );
                homePage.addPost(homePost);
            }

            return new ResponseEntity<>(homePage, HttpStatus.OK);
        }
    }
}
