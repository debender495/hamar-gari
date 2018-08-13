package com.deb.gari.importapiimpl;

import com.deb.gari.csvreader.CsvReader;
import com.deb.gari.importapi.ImportApiService;
import com.deb.gari.model.bean.RecordBean;
import com.deb.gari.model.bean.MapResult;
import com.deb.gari.unmarshal.RecordUnmarshal;
import generated.RecordsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Debender Prasad
 */
@Service
public class ImportApiServiceImpl implements ImportApiService {


    private RecordUnmarshal recordUnmarshal;
    private CsvReader csvReader;

    @Autowired
    public ImportApiServiceImpl(RecordUnmarshal recordUnmarshal,
                                CsvReader csvReader) {
        this.recordUnmarshal = recordUnmarshal;
        this.csvReader = csvReader;
    }

    @Override
    public MapResult importRecords(InputStream inputStream, String filename) {

        final List<RecordBean> recordBeans = filename
                .endsWith("xml") ? getRecordBeans(recordUnmarshal.xmlToRecord(inputStream)) : csvReader.csvTorecord(inputStream);

        final List<RecordBean> recordWithDuplicateReference = findRecordWithDuplicateReference(recordBeans);
        final List<RecordBean> recordWithWrongEndbalance = findRecordWithWrongEndbalance(recordBeans);
        MapResult mapResult = new MapResult();
        mapResult.getResultObjectMap().put(
                "Failed Records with duplicate reference number are",
                recordWithDuplicateReference);
        mapResult.getResultObjectMap().put(
                "Failed Records with wrong end balance",
                recordWithWrongEndbalance);
        return mapResult;
    }


    private List<RecordBean> findRecordWithWrongEndbalance(List<RecordBean> recordBeans ) {
        return recordBeans
                .stream()
                .filter(recordBean ->
                        this.validateEndbalance(
                                recordBean.getStartBalance()
                                , recordBean.getMutation(),
                                recordBean.getEndBalance()
                        )
                )
                .collect(Collectors.toList());
    }

    public boolean validateEndbalance(String startBalance,
                                      String mutation,
                                      String endBalance) {

        return !getNumericValue(startBalance)
                .add(getNumericValue(mutation))
                .equals(getNumericValue(endBalance));
    }

    private BigDecimal getNumericValue(String startBalance) {
        return BigDecimal.valueOf(Double.parseDouble(startBalance));
    }

    private List<RecordBean> findRecordWithDuplicateReference(List<RecordBean> recordBeans) {
        List<RecordBean> recordBeansDuplicate;
        List<RecordBean> recordBeansDistinct = new ArrayList<>();

        Observable.from(recordBeans).distinct(RecordBean::getReference).subscribe(recordBean ->
                {
                    recordBeansDistinct.add(recordBean);
                }, throwable -> new Exception()
        );

        recordBeansDuplicate = recordBeans
                .stream()
                .filter(elem -> !recordBeansDistinct.contains(elem))
                .collect(Collectors.toList());

        return recordBeansDuplicate;

    }

    private List<RecordBean> getRecordBeans(RecordsType recordsType) {
        return recordsType.getRecord().stream().map(item -> new RecordBean(
                item.getAccountNumber(),
                item.getDescription(),
                item.getStartBalance(),
                item.getMutation(),
                item.getEndBalance(),
                item.getReference())).collect(Collectors.toList());
    }

}
