package fsac.ms3i.zinger.repository;

import fsac.ms3i.zinger.model.Report;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportRepository extends MongoRepository<Report, String> {
}
