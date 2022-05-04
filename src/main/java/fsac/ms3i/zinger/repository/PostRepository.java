package fsac.ms3i.zinger.repository;

import fsac.ms3i.zinger.Model.Post;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post,String> {

}
