package com.deb.gari.unmarshal;

import generated.RecordsType;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;

/**
 * @author Debender Prasad
 */
@Service
public class RecordUnmarshal {

    public RecordsType xmlToRecord(InputStream inputStream) {

        RecordsType recordsType = null;
        JAXBContext jaxbContext;
        Unmarshaller unmarshaller;

        try {

            jaxbContext = JAXBContext.newInstance(RecordsType.class);
            unmarshaller = jaxbContext.createUnmarshaller();

            Source source = new StreamSource(inputStream);
            JAXBElement<RecordsType> recordsTypeJAXBElement = unmarshaller.unmarshal(source, RecordsType.class);

            recordsType = recordsTypeJAXBElement.getValue();

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return recordsType;
    }
}
