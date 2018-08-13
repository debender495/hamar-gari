package com.deb.gari.model.bean;

import com.deb.gari.model.Record;
import lombok.*;

/**
 * @author Debender Prasad
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecordBean implements Record{

    private String accountNumber;

    private String description;

    private String startBalance;

    private String mutation;

    private String endBalance;

    private String reference;
}
