package code.kliangh.xml.utils;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class XmlReaderTest {
    private static final String XML_PATH = "./src/test/resources/xml_file_system/doctor_profile.xml";
    private static final String INCORRECT_XML_PATH = "./src/test/resources/xml_file_system/";

    private XmlReader xmlReader;

    @Before
    public void setUp() {
        xmlReader = new XmlReader();
    }

    @Test
    public void readXML() throws IOException, SAXException, ParserConfigurationException {
        Document xmlDocument = xmlReader.readXML(XML_PATH);

        assertTrue(xmlDocument instanceof Document);
    }

    @Test
    public void readXMLWithIncorrectPath() {
        Document nullXmlDocument = null;
        try {
            nullXmlDocument = xmlReader.readXML(INCORRECT_XML_PATH);
        } catch (ParserConfigurationException e) {
            assertTrue(e instanceof  ParserConfigurationException);
        } catch (SAXException e) {
            assertTrue(e instanceof SAXException);
        } catch (IOException e) {
            assertTrue(e instanceof IOException);
        }

        assertNull(nullXmlDocument);
    }

}