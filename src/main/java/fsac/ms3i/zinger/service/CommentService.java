package fsac.ms3i.zinger.service;

import fsac.ms3i.zinger.exception.CommentCollectionException;
import fsac.ms3i.zinger.model.Comment;

import javax.validation.ConstraintViolationException;
import java.util.List;

public interface CommentService {
    public List<Comment> getComments();
    public Comment getComment(String id) throws CommentCollectionException;
    public Comment createComment(Comment comment) throws ConstraintViolationException, CommentCollectionException;
    public void deleteComment(String id) throws CommentCollectionException;
}
