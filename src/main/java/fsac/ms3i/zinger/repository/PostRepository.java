package fsac.ms3i.zinger.repository;

import fsac.ms3i.zinger.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
