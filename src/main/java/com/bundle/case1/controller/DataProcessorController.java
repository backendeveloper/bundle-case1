package com.bundle.case1.controller;

import com.bundle.case1.service.DataProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DataProcessorController {

    private final DataProcessorService dataProcessorService;

    @Autowired
    public DataProcessorController(DataProcessorService dataProcessorService) {
        this.dataProcessorService = dataProcessorService;
    }

    @PostMapping("/processData")
    public List<Integer> getProcessData(@RequestBody List<List<String>> data) {
        return dataProcessorService.processData(data);
    }
}
