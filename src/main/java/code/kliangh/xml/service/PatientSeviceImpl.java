package code.kliangh.xml.service;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import static code.kliangh.xml.utils.XmlUtils.getMaxID;

public class PatientSeviceImpl implements PatientService {

    public Document newPatient(Document document, String name){

        String patientId = getMaxID(document, "patient", "p_id");

        Element root_element = document.getDocumentElement();

        Element new_patient=document.createElement("patient");
        new_patient.setAttribute("p_id", patientId);
        new_patient.setAttribute("name", name);
        root_element.appendChild(new_patient);

        return document;
    }

    @Override
    public Node getPatient(Document doc, String patientId) throws XPathExpressionException {

        if(patientId != null){
            String query_expression = "/medical_record/patient[@p_id='"+ patientId +"']";
            XPath xPath =  XPathFactory.newInstance().newXPath();

            Node patient;

            patient = (Node) xPath.compile(query_expression).evaluate(doc, XPathConstants.NODE);
            if(patient == null) {
                System.out.println("No data found!!");
            }

            return patient;

        } else {
            NodeList patient_nodelist = doc.getElementsByTagName("patient");
            for(int x=0,size= patient_nodelist.getLength(); x<size; x++) {
                String query_p_id = patient_nodelist.item(x).getAttributes().getNamedItem("p_id").getNodeValue();
                String name = patient_nodelist.item(x).getAttributes().getNamedItem("name").getNodeValue();
                System.out.println("P_ID: " + query_p_id + ", Name: " + name);
            }

        }return null;
    }

    public Document updatePatientName(Document document, String patientId, String updateName) throws XPathExpressionException {

        String query_expression = "/medical_record/patient[@p_id='"+ patientId +"']";

        XPath xPath = XPathFactory.newInstance().newXPath();
        Element record_element = (Element) xPath.compile(query_expression).evaluate(document, XPathConstants.NODE);

        if (record_element != null) {
            record_element.setAttribute("name", updateName);
        }

        return document;
    }


}
