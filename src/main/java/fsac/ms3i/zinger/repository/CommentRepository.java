package fsac.ms3i.zinger.repository;

import fsac.ms3i.zinger.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
