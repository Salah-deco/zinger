package fsac.ms3i.zinger.service;

import fsac.ms3i.zinger.exception.CommentCollectionException;
import fsac.ms3i.zinger.exception.PostCollectionException;
import fsac.ms3i.zinger.exception.UserCollectionException;
import fsac.ms3i.zinger.model.Comment;
import fsac.ms3i.zinger.model.Post;
import fsac.ms3i.zinger.repository.CommentRepository;
import fsac.ms3i.zinger.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileServiceImp implements ProfileService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Post> getPostsOfUser(String userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        if (posts.size() > 0) {
            return posts;
        } else {
            return new ArrayList<Post>();
        }
    }

    @Override
    public List<Comment> getCommentsOfPost(String postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        if (comments.size() > 0) {
            return comments;
        } else {
            return new ArrayList<Comment>();
        }
    }
}
