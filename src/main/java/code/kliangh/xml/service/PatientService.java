package code.kliangh.xml.service;

import code.kliangh.xml.exception.ServiceException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpressionException;

public interface PatientService {

    public Document newPatient(Document document, String name);

    public Node getPatient(Document document, String patientId) throws XPathExpressionException, ServiceException;

    public Document updatePatientName(Document document, String patientId, String updateName) throws XPathExpressionException;
}
