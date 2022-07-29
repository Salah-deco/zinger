package fsac.ms3i.zinger.service;

import fsac.ms3i.zinger.exception.CommentCollectionException;
import fsac.ms3i.zinger.exception.PostCollectionException;
import fsac.ms3i.zinger.exception.UserCollectionException;
import fsac.ms3i.zinger.model.Comment;
import fsac.ms3i.zinger.model.Post;

import java.util.List;

public interface ProfileService {
    public List<Post> getPostsOfUser(String userId);
    public List<Comment> getCommentsOfPost(String postId);
}
