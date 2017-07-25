package code.kliangh.xml.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import static org.junit.jupiter.api.Assertions.*;

class XmlReaderTest {
    private static final String xmlPath = "/Users/kliangh/Projects/project_IntelliJ/repo_Craftsman/xml-file-rw-example/testXml.xml";
    private static final String incorrectXmlPath = "/Users/kliangh/Projects/project_IntelliJ/repo_Craftsman/xml-file-rw-example/";

    private XmlReader xmlReader;

    @BeforeEach
    void setUp() {
        xmlReader = new XmlReader();
    }

    @Test
    void readXML() {
        Document xmlDocument = xmlReader.readXML(xmlPath);
        Document nullXmlDocument = xmlReader.readXML(incorrectXmlPath);

        assertNotNull(xmlDocument);
        assertNull(nullXmlDocument);
    }

}