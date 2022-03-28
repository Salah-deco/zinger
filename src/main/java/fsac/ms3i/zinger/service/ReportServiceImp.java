package fsac.ms3i.zinger.service;

import fsac.ms3i.zinger.exception.ReportCollectionException;
import fsac.ms3i.zinger.model.Report;
import fsac.ms3i.zinger.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImp implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public List<Report> getReports() {
        List<Report> reports = reportRepository.findAll();
        if (reports.size() > 0) {
            return reports;
        } else {
            return new ArrayList<Report>();
        }
    }

    @Override
    public Report getReport(String id) throws ReportCollectionException {
        Optional<Report> reportOptional = reportRepository.findById(id);
        if (!reportOptional.isPresent()) {
            throw new ReportCollectionException(ReportCollectionException.NotFoundException(id));
        } else {
            return reportOptional.get();
        }
    }

    @Override
    public Report createReport(Report report) throws ConstraintViolationException, ReportCollectionException {
        report.setReportAt(new Date(System.currentTimeMillis()));
        // more validation

        // condition if userId and postId exists

        // save
        reportRepository.save(report);
        return report;
    }

    @Override
    public void deleteReport(String id) throws ReportCollectionException {
        Optional<Report> reportOptional = reportRepository.findById(id);
        if (!reportOptional.isPresent()) {
            throw new ReportCollectionException(ReportCollectionException.NotFoundException(id));
        } else {
            reportRepository.deleteById(id);
            // ...
        }
    }
}
