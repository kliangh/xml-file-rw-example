package code.kliangh.xml.service;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpressionException;

public interface PatientService {

    public Document newPatient(Document document, String name);

    public Node getPatient(Document document, String patientId) throws XPathExpressionException;

    public Document updatePatientName(Document document, String patientId, String updateName) throws XPathExpressionException;
}
