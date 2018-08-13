package com.deb.gari.dto;

import com.deb.gari.model.bean.RecordBean;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Debender Prasad
 */
@Getter
@Setter
public class ReportDTO {

    private Date generatedDate;
    List<RecordBean> recordWithDuplicateReference = new ArrayList<>();
    List<RecordBean> recordWithWrongEndbalance = new ArrayList<>();
}
