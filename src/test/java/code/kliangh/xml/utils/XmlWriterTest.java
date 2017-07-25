package code.kliangh.xml.utils;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static org.junit.Assert.assertTrue;

class XmlWriterTest {
    private Document xmlDocument;
    private XmlWriter xmlWriter;

    @BeforeEach
    void setUp() {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }


        xmlDocument = docBuilder.newDocument();
        xmlWriter = new XmlWriter();
    }

    @Test
    void saveToXML() {
        assertTrue(xmlWriter.saveToXML(xmlDocument, "testXml.xml", "testXml.dtd"));
    }

}