package code.kliangh.xml.utils;

import org.w3c.dom.Document;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by kliangh on 28/06/2017.
 */
public class XmlWriter {

    public boolean saveToXML(Document document, String xmlFilename, String dtdFilename) {

        DOMSource source = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;

        try {
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, dtdFilename);
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            StreamResult streamResult = new StreamResult(xmlFilename);

            transformer.transform(source, streamResult);
        } catch(TransformerConfigurationException transformerConfigurationException) {
            transformerConfigurationException.printStackTrace();
        } catch(TransformerException transformerException) {
            transformerException.printStackTrace();
        }

        return true;
    }
}
