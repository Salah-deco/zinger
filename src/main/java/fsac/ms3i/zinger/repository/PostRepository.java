package fsac.ms3i.zinger.repository;

import fsac.ms3i.zinger.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

//    @Query("{'userI': ?0}")
    List<Post> findByUserId(String userId);
}
