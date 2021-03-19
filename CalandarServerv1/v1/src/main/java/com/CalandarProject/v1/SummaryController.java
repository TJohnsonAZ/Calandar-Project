package com.CalandarProject.v1;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class SummaryController {

    @GetMapping("/summary")
    public Summary summary(@RequestParam(value = "startDate") int date) {
        return new Summary(date);
    }
}
