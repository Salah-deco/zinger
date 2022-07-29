package fsac.ms3i.zinger.repository;

import fsac.ms3i.zinger.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findByPostId(String postId);
}
