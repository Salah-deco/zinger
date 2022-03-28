package fsac.ms3i.zinger.service;

import fsac.ms3i.zinger.exception.CommentCollectionException;
import fsac.ms3i.zinger.model.Comment;
import fsac.ms3i.zinger.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getComments() {
        List<Comment> comments = commentRepository.findAll();
        if (comments.size() > 0) {
            return comments;
        } else {
            return new ArrayList<Comment>();
        }
    }

    @Override
    public Comment getComment(String id) throws CommentCollectionException {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent()) {
            throw new CommentCollectionException(CommentCollectionException.NotFoundException(id));
        } else {
            return optionalComment.get();
        }
    }

    @Override
    public Comment createComment(Comment comment) throws ConstraintViolationException, CommentCollectionException {
        comment.setCreatedAt(new Date(System.currentTimeMillis()));
        // more validation

        // condition if userId and postId exists

        // save
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public void deleteComment(String id) throws CommentCollectionException {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent()) {
            throw new CommentCollectionException(CommentCollectionException.NotFoundException(id));
        } else {
            commentRepository.deleteById(id);
            // delete comment from list comment in User model
        }
    }
}
