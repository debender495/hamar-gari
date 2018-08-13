package com.deb.gari.exportapiimpl;

import com.deb.gari.configuration.ReportGenConfiguration;
import com.deb.gari.dto.ReportDTO;
import com.deb.gari.exportapi.ExportApiService;
import com.deb.gari.model.bean.RecordBean;
import com.deb.gari.model.bean.MapResult;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Debender Prasad
 */
@Service
public class ExportApiServiceImpl implements ExportApiService {

    private ReportGenConfiguration reportGenConfiguration;

    @Autowired
    public ExportApiServiceImpl(ReportGenConfiguration reportGenConfiguration) {
        this.reportGenConfiguration = reportGenConfiguration;
    }

    /**
     * Exports the data to an specific format.
     *
     * @param mapResult
     * @return The processed validated report data
     */
    @Override
    public InputStream exportValidatedReport(MapResult mapResult) throws Exception {
        try {
            ByteArrayOutputStream outputStream = getOutputStream(mapResult);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (TemplateException | IOException e) {
            throw new Exception(String.format("Could not generate report file %s with error %s", mapResult
            ), e);
        }
    }

    private ByteArrayOutputStream getOutputStream(MapResult mapResult) throws TemplateException, IOException {
        List<RecordBean> recordWithDuplicateReference = (List<RecordBean>) mapResult.getResultObjectMap()
                .get("Failed Records with duplicate reference number are");
        List<RecordBean> recordWithWrongEndbalance = (List<RecordBean>) mapResult.getResultObjectMap()
                .get("Failed Records with wrong end balance");
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setGeneratedDate(Calendar.getInstance().getTime());
        reportDTO.getRecordWithDuplicateReference().addAll(recordWithDuplicateReference);
        reportDTO.getRecordWithWrongEndbalance().addAll(recordWithWrongEndbalance);
        final Configuration configuration = reportGenConfiguration.freemarkerConfiguration();
        Template temp = configuration.getTemplate("report_template.txt");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Map<String, Object> root = new HashMap<>();
        root.put("reportDTO", reportDTO);
        temp.process(root, new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        return outputStream;
    }
}
