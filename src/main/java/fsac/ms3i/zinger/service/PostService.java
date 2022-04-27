package fsac.ms3i.zinger.service;

import fsac.ms3i.zinger.exception.PostCollectionException;
import fsac.ms3i.zinger.model.Post;

import javax.validation.ConstraintViolationException;
import java.util.List;

public interface PostService {
    public List<Post> getPosts();
    public Post getPost(String id) throws PostCollectionException;
    public Post createPost(Post post) throws ConstraintViolationException, PostCollectionException;
    public void updatePost(String id, Post post) throws ConstraintViolationException, PostCollectionException;
    public void deletePost(String id) throws PostCollectionException;
}
