package code.kliangh.xml.service;

import code.kliangh.xml.exception.ServiceException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import static code.kliangh.xml.utils.XmlUtils.getMaxID;

public class PatientServiceImpl implements PatientService {

    public Document newPatient(Document document, String name){

        String patientId = getMaxID(document, "patient", "p_id");

        Element rootElement = document.getDocumentElement();

        Element newPatient = document.createElement("patient");
        newPatient.setAttribute("p_id", patientId);
        newPatient.setAttribute("name", name);
        rootElement.appendChild(newPatient);

        return document;
    }

    @Override
    public Node getPatient(Document doc, String patientId) throws XPathExpressionException, ServiceException {

        if(patientId != null){
            String queryExpression = "/medical_record/patient[@p_id='"+ patientId +"']";
            XPath xPath =  XPathFactory.newInstance().newXPath();

            Node patient;

            patient = (Node) xPath.compile(queryExpression).evaluate(doc, XPathConstants.NODE);
            if(patient == null) {
                throw new ServiceException("No data found!!");
            }

            return patient;

        } else {
            NodeList patientNodeList = doc.getElementsByTagName("patient");
            for(int x=0,size= patientNodeList.getLength(); x<size; x++) {
                String query_p_id = patientNodeList.item(x).getAttributes().getNamedItem("p_id").getNodeValue();
                String name = patientNodeList.item(x).getAttributes().getNamedItem("name").getNodeValue();
                System.out.println("P_ID: " + query_p_id + ", Name: " + name);
            }

        }return null;
    }

    public Document updatePatientName(Document document, String patientId, String updateName) throws XPathExpressionException {

        String queryExpression = "/medical_record/patient[@p_id='"+ patientId +"']";

        XPath xPath = XPathFactory.newInstance().newXPath();
        Element recordElement = (Element) xPath.compile(queryExpression).evaluate(document, XPathConstants.NODE);

        if (recordElement != null) {
            recordElement.setAttribute("name", updateName);
        }

        return document;
    }


}
