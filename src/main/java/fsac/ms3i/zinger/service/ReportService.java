package fsac.ms3i.zinger.service;

import fsac.ms3i.zinger.exception.ReportCollectionException;
import fsac.ms3i.zinger.model.Report;

import javax.validation.ConstraintViolationException;
import java.util.List;

public interface ReportService {
    public List<Report> getReports();
    public Report getReport(String id) throws ReportCollectionException;
    public Report createReport(Report report) throws ConstraintViolationException, ReportCollectionException;
    public void deleteReport(String id) throws ReportCollectionException;
}
