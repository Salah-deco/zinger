package fsac.ms3i.zinger.repository;

import fsac.ms3i.zinger.model.Post;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	@Query("{'isBlocked': true}")
	List<Post> findisBlocked();
}

