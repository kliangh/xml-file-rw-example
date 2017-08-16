package code.kliangh.xml.service;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpressionException;
import java.util.HashMap;

public interface MedicalRecordService {
    public Document addRecord(Document document, String patientId, String doctorId, HashMap medical_record) throws XPathExpressionException;

    public Node getRecord(Document document, String patientId , String medicalRecordId) throws XPathExpressionException;

    public Document updateRecord(Document document, String patientId, String medicalRecordId, String recordItem, String elementContent) throws XPathExpressionException;

    public Document deleteRecord(Document document, String patientId, String medicalRecordId) throws XPathExpressionException;
}
