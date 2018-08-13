package com.deb.gari.csvreader;

import com.deb.gari.model.bean.RecordBean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Debender Prasad
 */
@Service
public class CsvReader {

    public final List<RecordBean> csvTorecord(InputStream inputStream) {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        List<RecordBean> recordBeans = new ArrayList<>();

        try {
            br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count > 0) {
                    String[] records = line.split(cvsSplitBy);
                    recordBeans.add(new RecordBean(records[1], records[2], records[3], records[4], records[5], records[0]));
                }
                count++;


            }
        } catch (IOException e) {
        }
        return recordBeans;
    }
}
