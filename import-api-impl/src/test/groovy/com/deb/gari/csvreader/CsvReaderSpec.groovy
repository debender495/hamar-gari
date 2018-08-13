package com.deb.gari.csvreader

import spock.lang.Specification

/**
 * @author Debender Prasad
 */
class CsvReaderSpec extends Specification {

    def 'should parse and read csv records to records type'() {
        given:
        def csv = CsvReaderSpec.getClassLoader().getResourceAsStream("records.csv")

        when:
        CsvReader csvReader = new CsvReader()
        def rb = csvReader.csvTorecord(csv)

        then:
        rb.size() == 10;

        and:
        def record = rb.find { it -> it.accountNumber == 'NL27SNSB0917829871' }
        record.accountNumber == 'NL27SNSB0917829871'
        record.reference == '163590'
        record.description == 'Tickets from Rik Bakker'
        record.mutation == '+29.87'
        record.startBalance == '105.11'
        record.endBalance == '134.98'
    }
}
