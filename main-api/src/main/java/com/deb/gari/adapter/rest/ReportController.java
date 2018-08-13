package com.deb.gari.adapter.rest;

import com.deb.gari.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Debender Prasad
 */
@RestController
@RequestMapping(value = "api/current/generate")
@Api(value = "/api/current/generate", description = "Custom Controller")
public class ReportController {

    private ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Validates the imported file and creates a report which will display both the transaction reference and
     * description of each of the failed records
     *
     * @param inputFile    monthly deliveries of customer statement records
     * @return the validated report
     */
    @PostMapping(value = "/validationReport")
    @ApiOperation(value = "Validates the imported file.")
    public ResponseEntity<Resource> generateValidationReport(@RequestParam("file") MultipartFile inputFile)
            throws IOException {

        InputStream inputStream = reportService.generateReport(inputFile.getInputStream(), inputFile.getOriginalFilename());

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.TEXT_PLAIN)
                .body(new InputStreamResource(inputStream));
    }
}
