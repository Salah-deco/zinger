package fsac.ms3i.zinger.service;

import fsac.ms3i.zinger.exception.PostCollectionException;
import fsac.ms3i.zinger.exception.UserCollectionException;
import fsac.ms3i.zinger.model.Post;
import fsac.ms3i.zinger.model.User;
import fsac.ms3i.zinger.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.HashMap;

@Service
public class PostServiceImp implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;


    @Override
    public Post createPost(Post post) throws ConstraintViolationException, PostCollectionException {
        post.setCreatedAt(new Date(System.currentTimeMillis()));
        post.setBlocked(false);
        post.setComments(new ArrayList<>());
        post.setReactions(new HashMap<>());
        post.setReports(new ArrayList<>());
        // more validation
        try {
            User createBy = userService.getUser(post.getUserId());
        } catch (UserCollectionException e) {
            throw new PostCollectionException(PostCollectionException.PostCreateByInvalidUser(post.getUserId()));
        }

        // save
        postRepository.save(post);
        return post;
    }

    @Override
    public List<Post> getPosts() {
        List<Post> posts = postRepository.findAll();
        if (posts.size() > 0) {
            return posts;
        } else {
            return new ArrayList<Post>();
        }
    }

    @Override
    public Post getPost(String id) throws PostCollectionException {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (!optionalPost.isPresent()) {
            throw new PostCollectionException(PostCollectionException.NotFoundException(id));
        } else {
            return optionalPost.get();
        }
    }

    @Override
    public void updatePost(String id, Post post) throws ConstraintViolationException, PostCollectionException {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post postToUpdate = optionalPost.get();

            postToUpdate.setBody(post.getBody() != null ? post.getBody() : postToUpdate.getBody());
            postToUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));

            postRepository.save(postToUpdate);
        } else {
            throw new PostCollectionException(PostCollectionException.NotFoundException(id));
        }
    }

    @Override
    public void deletePost(String id) throws PostCollectionException {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (!optionalPost.isPresent()) {
            throw new PostCollectionException(PostCollectionException.NotFoundException(id));
        } else {
            postRepository.deleteById(id);
            // Delete all comments

            // Delete all reactions

            // Delete all reports

            // Delete post from list post in User model
        }
    }
}
