package fsac.ms3i.zinger.controller;

import fsac.ms3i.zinger.Model.Comment;
import fsac.ms3i.zinger.Model.Report;
import  fsac.ms3i.zinger.repository.ReportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class ReportController {
    @Autowired
    private ReportRepository  Report;
    @PostMapping("/Report")
    public ResponseEntity<?> postComment(@RequestBody Report repo){
        try {

            repo.setReportAt(new Date(System.currentTimeMillis()));
            Report.save(repo);

            return new ResponseEntity<Report>(repo, HttpStatus.OK );
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }




}
