package fsac.ms3i.zinger.repository;

import fsac.ms3i.zinger.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
