package code.kliangh.xml.utils;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by kliangh on 28/06/2017.
 */
public class XmlReeder {

    public Document readXML(String xmlPath) {

        Document document;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(xmlPath);

            return document;

        } catch(ParserConfigurationException parserConfigurationException) {
            parserConfigurationException.printStackTrace();
        } catch(SAXException saxException) {
            saxException.printStackTrace();
        } catch(IOException ioException) {
            ioException.printStackTrace();
        }

        return null;
    }
}
