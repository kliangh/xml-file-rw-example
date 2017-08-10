package code.kliangh.xml.utils;

import org.w3c.dom.Document;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by kliangh on 28/06/2017.
 */
public class XmlWriter {

    public void saveToXML(Document document, String xmlFileName, String dtdFileName) throws TransformerException {

        DOMSource source = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;

        transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, dtdFileName);
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        StreamResult streamResult = new StreamResult(xmlFileName);

        transformer.transform(source, streamResult);
    }
}
