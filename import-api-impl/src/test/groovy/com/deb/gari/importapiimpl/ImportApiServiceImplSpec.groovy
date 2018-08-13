package com.deb.gari.importapiimpl

import com.deb.gari.csvreader.CsvReader
import com.deb.gari.model.bean.MapResult
import com.deb.gari.model.bean.RecordBean
import com.deb.gari.unmarshal.RecordUnmarshal
import spock.lang.Specification

/**
 * @author Debender Prasad
 */
class ImportApiServiceImplSpec extends Specification {

    RecordUnmarshal recordUnmarshalMock = new RecordUnmarshal()
    CsvReader csvReader = Mock()
    ImportApiServiceImpl importApiServiceImpl = new ImportApiServiceImpl(recordUnmarshalMock, csvReader)

    def 'should return a map of validated report'() {
        given:
        def filename = "records.xml";
        def inputStream = ImportApiServiceImplSpec.getClassLoader().getResourceAsStream(filename)



        when:
        MapResult resultMap = importApiServiceImpl.importRecords(inputStream, filename)

        then:
        List<RecordBean> recordWithDuplicateReference = resultMap.getResultObjectMap().get("Failed Records with duplicate reference number are")
        List<RecordBean> recordWithWrongEndbalance = resultMap.getResultObjectMap().get("Failed Records with wrong end balance")

        recordWithDuplicateReference.size() == 3
        recordWithWrongEndbalance.size() == 2

    }
}
