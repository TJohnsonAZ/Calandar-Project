package com.CalandarProject.v1;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class SummaryController {

    // get request handler that creates and returns a summary object using given data
    @GetMapping("/summary")
    public Summary summary(@RequestParam(value = "startDate") int start,
                           @RequestParam(value = "endDate") int end,
                           @RequestParam(value = "user") String user) {
        return new Summary(start, end, user);
    }
}
