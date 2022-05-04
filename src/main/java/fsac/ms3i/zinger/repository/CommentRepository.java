package fsac.ms3i.zinger.repository;

import fsac.ms3i.zinger.Model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommentRepository  extends MongoRepository <Comment,String>{
    @Query("{'postId': ?0}")
    List<Comment> findBypostid(String  postId);


}
