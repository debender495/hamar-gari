package com.deb.gari.unmarshal

import generated.RecordType
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Debender Prasad
 */
class RecordUnmarshalSpec extends Specification {

    def 'should unmarshall records xml to records type'() {
        given:
        def xml = RecordUnmarshalSpec.getClassLoader().getResourceAsStream("records.xml")

        when:
        RecordUnmarshal r = new RecordUnmarshal()
        def rt = r.xmlToRecord(xml)

        then:
        rt.getRecord().size() == 10;

        and:
        def record = rt.getRecord().find { it -> it.accountNumber == 'NL91RABO0315273637' }
        record.accountNumber == 'NL91RABO0315273637'
        record.reference == '187997'
        record.description == 'Clothes for Rik King'
        record.mutation == '-32.98'
        record.startBalance == '57.6'
        record.endBalance == '24.62'
    }

    @Unroll
    def 'Record with reference #ReferenceID should have data as #AccountNumberID #DescriptionID #StartBalanceID #MutationID #EndBalanceID'() {
        given:
        def xml = RecordUnmarshalSpec.getClassLoader().getResourceAsStream("records.xml")

        when:
        RecordUnmarshal r = new RecordUnmarshal()
        def result = r.xmlToRecord(xml)

        then:
        result.getRecord().size() == 10;

        RecordType record = result.getRecord().find { it -> it.reference == ReferenceID }
        record.accountNumber == AccountNumberID
        record.description == DescriptionID
        record.startBalance == StartBalanceID
        record.mutation == MutationID
        record.endBalance == EndBalanceID

        where:
        ReferenceID | AccountNumberID      | DescriptionID                     | StartBalanceID | MutationID || EndBalanceID
        '187997'    | 'NL91RABO0315273637' | 'Clothes for Rik King'            | '57.6'         | '-32.98'   || '24.62'
        '154270'    | 'NL56RABO0149876948' | 'Candy for Peter de Vries'        | '5429'         | '-939'     || '6368'
        '162197'    | 'NL90ABNA0585647886' | 'Tickets for Daniël de Vries'     | '95.03'        | '+48.33'   || '143.36'
        '129635'    | 'NL27SNSB0917829871' | 'Clothes for Vincent King'        | '14.48'        | '+16.39'   || '30.87'
        '148503'    | 'NL93ABNA0585619023' | 'Subscription from Willem Dekker' | '30.54'        | '-13.18'   || '17.36'
        '163023'    | 'NL43AEGO0773393871' | 'Tickets for Daniël de Vries'     | '37.79'        | '-40.84'   || '-3.05'

    }
}
