package code.kliangh.xml.service;

import code.kliangh.xml.utils.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.util.HashMap;

public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Override
    public Document addRecord(Document document, String patientId, String doctorId, HashMap medicalRecord) throws XPathExpressionException {

        String medicalRecordId = XmlUtils.getMaxID(document, "record", "r_id");
        String queryExpression = "/medical_record/patient[@p_id='"+ patientId +"']";
        XPath xPath =  XPathFactory.newInstance().newXPath();
        Node patient = (Node) xPath.compile(queryExpression).evaluate(document, XPathConstants.NODE);
        Element newRecord = document.createElement("record");
        newRecord.setAttribute("r_id", medicalRecordId);
        newRecord.setAttribute("d_id", doctorId);

        Element recordDescriptionElement = document.createElement("description");
        recordDescriptionElement.appendChild(document.createTextNode((String) medicalRecord.get("description")));
        newRecord.appendChild(recordDescriptionElement);

        Element recordDiagnosisElement = document.createElement("diagnosis");
        recordDiagnosisElement.appendChild(document.createTextNode((String) medicalRecord.get("diagnosis")));
        newRecord.appendChild(recordDiagnosisElement);

        Element recordTreatmentElement = document.createElement("treatment");
        recordTreatmentElement.appendChild(document.createTextNode((String) medicalRecord.get("treatment")));
        newRecord.appendChild(recordTreatmentElement);

        Element recordPrescriptionElement = document.createElement("prescription");
        recordPrescriptionElement.appendChild(document.createTextNode((String) medicalRecord.get("prescription")));
        newRecord.appendChild(recordPrescriptionElement);

        patient.appendChild(newRecord);

        return document;
    }

    @Override
    public Node getRecord(Document document, String patientId, String medicalRecordId) throws XPathExpressionException {

        String queryExpression = "/medical_record/patient[@p_id='"+ patientId +"']/record[@r_id='"+ medicalRecordId +"']";
        XPath xPath =  XPathFactory.newInstance().newXPath();

        Node node = (Node) xPath.compile(queryExpression).evaluate(document, XPathConstants.NODE);
        if(node == null) {
            System.out.println("No data found!!");
        }

        return node;
    }

    @Override
    public Document updateRecord(Document document, String patientId, String medicalRecordId, String recordItem, String elementContent) throws XPathExpressionException {
        String queryExpression = "/medical_record/patient[@p_id='"+ patientId +"']/record[@r_id='"+ medicalRecordId +"']/"+ recordItem;

        XPath xPath = XPathFactory.newInstance().newXPath();
        Element recordElement;

        recordElement = (Element) xPath.compile(queryExpression).evaluate(document, XPathConstants.NODE);

        if (recordElement != null) {
            recordElement.setTextContent(elementContent);
        }

        return document;
    }

    @Override
    public Document deleteRecord(Document document, String patientId, String medicalRecordId) throws XPathExpressionException {
        String queryExpression = "/medical_record/patient[@p_id='"+ patientId +"']/record[@r_id='"+ medicalRecordId +"']";

        XPath xPath = XPathFactory.newInstance().newXPath();
        Node recordNode = (Node) xPath.compile(queryExpression).evaluate(document, XPathConstants.NODE);

        while (recordNode.hasChildNodes())
            recordNode.removeChild(recordNode.getFirstChild());
        recordNode.getParentNode().removeChild(recordNode);

        return document;
    }

}
