package com.deb.gari.service;

import com.deb.gari.exportapi.ExportApiService;
import com.deb.gari.importapi.ImportApiService;
import com.deb.gari.model.bean.MapResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @author Debender Prasad
 */
@Component
public class ReportService {

    private final ImportApiService importerApiService;
    private final ExportApiService exportApiService;

    @Autowired
    public ReportService(ImportApiService importerApiService,
                         ExportApiService exportApiService) {
        this.importerApiService = importerApiService;
        this.exportApiService = exportApiService;
    }

    public InputStream generateReport(InputStream inputStream, String filename) {
        MapResult mapResult = importerApiService.importRecords(inputStream, filename);
        InputStream inputStreamOutPut = null;
        try {
            inputStreamOutPut = exportApiService.exportValidatedReport(mapResult);
        } catch (Exception e) {

        }
        return inputStreamOutPut;
    }
}
