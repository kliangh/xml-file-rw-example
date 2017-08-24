package code.kliangh.xml.utils;

import org.w3c.dom.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.HashMap;

public class XmlUtils {

    public static void printXMLTree(Document document) throws TransformerException {
        Transformer transformer;

        transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        //initialize StreamResult with File object to save to file
        StreamResult result = new StreamResult(new StringWriter());
        DOMSource source = new DOMSource(document);
        transformer.transform(source, result);
        String xmlString = result.getWriter().toString();
        System.out.println(xmlString);
    }

    private static void printIndent(int num) {
        System.out.print("  +");
        for (int i=0; i<=num; i++) System.out.print("-");
    }

    private static String  getID(Node node) {
        int type = node.getNodeType();
        switch(type) {
            case 1: return "Element：" + node.getNodeName();
            case 2: return "Attribute" + node.getAttributes();
            case 3: return "Value：" + node.getNodeValue().trim();
            case 4: return "CDATA Section：" + node.getNodeValue();
            case 6: return "Element Name：" + node.getNodeName();
            case 8: return "Comment：" + node.getNodeValue();
            case 9: return "Document：" + node.getNodeName();
            case 10: return "DOCTYPE：" + ((DocumentType) node).getSystemId();

            default: return "Not defined：" + type;
        }
    }

    public static String getMaxID(Document doc, String element_name, String attribute_name){

        NodeList nodelist = doc.getElementsByTagName(element_name);
        String maxId = "000";
        int x=0,size= nodelist.getLength();
        while (x<size) {
            String id_value = nodelist.item(x).getAttributes().getNamedItem(attribute_name).getNodeValue();
            if(Integer.parseInt(maxId) < Integer.parseInt(id_value))
                maxId = id_value;
            x++;
        }
        maxId = Integer.toString(Integer.parseInt(maxId)+1);

        return maxId;
    }

    private static void pChild(Node temp,int pos) {
        if ( temp.hasChildNodes() ) {
            NodeList nodes = temp.getChildNodes();
            //fetch all child nodes
            for (int i=0; i < nodes.getLength(); i++) {
                int type = nodes.item(i).getNodeType();
                printIndent(pos);
                System.out.println(getID(nodes.item(i)));
                if(type == Node.ELEMENT_NODE) {
                    pChild(nodes.item(i), pos+1);
                }
            }
        }
    }

    public static void showNodeData(Node node){

        Node child = (Node)node.getFirstChild();
        while (child != null) {
            System.out.println(getID(child));
            pChild(child, 0);
            child = child.getNextSibling();
        }
    }

    public static void convertXMLtoHTML(String xmlFilename, String xslFilename, String outputFilename) throws TransformerException, FileNotFoundException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        Source xslDoc = new StreamSource(xslFilename);
        Source xmlDoc = new StreamSource(xmlFilename);

        OutputStream htmlFile = new FileOutputStream(outputFilename);

        Transformer transformer = transformerFactory.newTransformer(xslDoc);
        transformer.transform(xmlDoc, new StreamResult(htmlFile));
    }
}
