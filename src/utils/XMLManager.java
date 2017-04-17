package utils;

import gens.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by ivans on 16/04/2017.
 */
public class XMLManager {

    public <T> void exportXMLObjectToFile(T object, String filename)
    {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
            jaxbMarshaller.marshal(object, new File("xmlFiles\\" + filename));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public <T> T importObjectFromXMLFile(T object, String filename)
    {
        File file = new File("xmlFiles\\"+filename);
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(object.getClass());
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            object = (T) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return object;
    }
}
