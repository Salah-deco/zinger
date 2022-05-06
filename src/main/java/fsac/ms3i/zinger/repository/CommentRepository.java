package fsac.ms3i.zinger.repository;

import fsac.ms3i.zinger.model.Comment;
import fsac.ms3i.zinger.model.Post;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
	
	@Query("{'isBlocked': true}")
	List<Comment> findisBlocked();
}
