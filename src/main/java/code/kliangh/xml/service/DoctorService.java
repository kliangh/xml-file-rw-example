package code.kliangh.xml.service;

import code.kliangh.xml.exception.ServiceException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpressionException;

public interface DoctorService {

    public Document newDoctor(Document document, String name, String department);

    public Node getDoctor(Document document, String doctorId) throws XPathExpressionException, ServiceException;

    public Document updateDoctorProfile(Document document, String doctorId, String updateElement ,String updateData) throws XPathExpressionException;
}
