package code.kliangh.xml.utils;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class XmlWriterTest {
    private static final String XML_FILE_NAME = "doctor_profile_outputted.xml";
    private static final String OUTPUTTED_XML_PATH = "./src/test/resources/xml_file_system/" + XML_FILE_NAME;
    private static final String DTD_PATH = "./src/test/resources/xml_file_system/doctor_profile.dtd";
    private Document xmlDocument;
    private XmlWriter xmlWriter;
    private XmlReader xmlReader;

    @Before
    public void setUp() {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }


        xmlDocument = docBuilder.newDocument();
        xmlWriter = new XmlWriter();
        xmlReader = new XmlReader();
    }

    @Test
    public void saveToXML() {
        try {
            xmlWriter.saveToXML(xmlDocument, OUTPUTTED_XML_PATH, DTD_PATH);
        } catch (TransformerException e) {
            assertTrue(e instanceof TransformerException);
        }

        try {
            assertNotNull(xmlReader.readXML(OUTPUTTED_XML_PATH));
        } catch (ParserConfigurationException e) {
            assertTrue(e instanceof ParserConfigurationException);
        } catch (SAXException e) {
            assertTrue(e instanceof SAXException);
        } catch (IOException e) {
            assertTrue(e instanceof IOException);
        }
    }

}