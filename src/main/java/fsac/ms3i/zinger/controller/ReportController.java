package fsac.ms3i.zinger.controller;

import fsac.ms3i.zinger.exception.CommentCollectionException;
import fsac.ms3i.zinger.exception.ReportCollectionException;
import fsac.ms3i.zinger.model.Comment;
import fsac.ms3i.zinger.model.Report;
import fsac.ms3i.zinger.service.ReportServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReportController {

    @Autowired
    private ReportServiceImp reportServiceImp;

    @GetMapping("/reports")
    public ResponseEntity<?> getReports() {
        List<Report> reports = reportServiceImp.getReports();
        return new ResponseEntity<>(reports, reports.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<?> getReport(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(reportServiceImp.getReport(id), HttpStatus.OK);
        } catch (ReportCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/report")
    public ResponseEntity<?> createReport(@RequestBody Report report) {
        try {
            Report createdReport = reportServiceImp.createReport(report);
            return new ResponseEntity<>(createdReport, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/report/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        try {
            reportServiceImp.deleteReport(id);
            return new ResponseEntity<>("Delete report with id = " + id, HttpStatus.OK);
        } catch (ReportCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
