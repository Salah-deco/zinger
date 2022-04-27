package fsac.ms3i.zinger.repository;

import fsac.ms3i.zinger.model.Comment;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

	List<Comment> findBypostId(String id);

}
