package com.deb.gari.exportapiimpl

import com.deb.gari.configuration.ReportGenConfiguration
import com.deb.gari.dto.ReportDTO
import com.deb.gari.model.bean.RecordBean
import com.deb.gari.model.bean.MapResult
import spock.lang.Specification

import java.nio.charset.StandardCharsets
import java.nio.file.Paths

/**
 * @author Debender Prasad
 */
class ExportApiServiceImplSpec extends Specification {


    def'generate report'(){

            given:
            String expectedReport = Paths.get(ClassLoader.getSystemResource("reports/output.txt").toURI()).getText(StandardCharsets.UTF_8.toString())
            Calendar generatedDate = Calendar.getInstance()
            generatedDate.set(2018, 0, 1, 12, 30)

            RecordBean recordBean = new RecordBean('NL91RABO0315273637','Clothes for Rik King ' ,'57.6 '           , '-32.98'         , '24.62'   , '187997');
            RecordBean recordBean1 = new RecordBean('NL91RABO0315273638','Clothes for Rik King bla ' ,'57.6 '           , '-32.98'         , '24.62'   , '187997');

            List<RecordBean> recordWithDuplicateReference = new ArrayList<>()

            recordWithDuplicateReference.add(recordBean)
            recordWithDuplicateReference.add(recordBean1)

            List<RecordBean> recordWithWrongEndbalance = new ArrayList<>()

            MapResult resultMap = new MapResult();
            resultMap.getResultObjectMap().put(
                    "Failed Records with duplicate reference number are",
                    recordWithDuplicateReference);
            resultMap.getResultObjectMap().put(
                    "Failed Records with wrong end balance",
                    recordWithWrongEndbalance);


            ExportApiServiceImpl exportApiService = new ExportApiServiceImpl(new ReportGenConfiguration())
            ReportDTO reportDTO = new ReportDTO()
            reportDTO.setGeneratedDate(generatedDate.getTime())

            when:
            ByteArrayOutputStream outputStream = exportApiService.getOutputStream(resultMap)

            then:
            new String(outputStream.toByteArray()).replace("\r","") == expectedReport


    }
}
