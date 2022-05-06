package fsac.ms3i.zinger.repository;

import fsac.ms3i.zinger.model.Report;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends MongoRepository<Report, String> {
	@Query("{ 'postId': ?0 }")
	List<Report> findBypostId(String id);
}
