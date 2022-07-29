package fsac.ms3i.zinger.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    @GetMapping("/test")
    public ResponseEntity<Map<String, String>> Test(@RequestHeader(value = "Authorization") String authorizationHeader,
                                                    HttpSession session) {

        Integer counter = (Integer) session.getAttribute("count");

        if (counter == null) {
            counter = 1;
        } else {
            counter++;
        }

        session.setAttribute("count", counter);

        Map<String, String> returnValue = new HashMap<>();
        returnValue.put("Authorization", authorizationHeader);
        returnValue.put("count", (String) session.getAttribute("count"));
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
