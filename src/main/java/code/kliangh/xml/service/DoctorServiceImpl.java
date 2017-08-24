package code.kliangh.xml.service;

import code.kliangh.xml.utils.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class DoctorServiceImpl implements DoctorService {

    @Override
    public Document newDoctor(Document document, String name, String department){

        String doctorId = XmlUtils.getMaxID(document, "doctor", "d_id");

        Element rootElement = document.getDocumentElement();

        Element newDoctor = document.createElement("doctor");
        newDoctor.setAttribute("d_id", doctorId);

        Element newDoctorName = document.createElement("name");
        newDoctorName.appendChild(document.createTextNode(name));
        newDoctor.appendChild(newDoctorName);

        Element newDoctorDepartment = document.createElement("department");
        newDoctorDepartment.appendChild(document.createTextNode(department));
        newDoctor.appendChild(newDoctorDepartment);

        rootElement.appendChild(newDoctor);

        return document;
    }


    @Override
    public Node getDoctor(Document document, String dcotorId) throws XPathExpressionException {

        if(dcotorId != null){

            String queryExpression = "/profile/doctor[@d_id='"+ dcotorId +"']";
            XPath xPath =  XPathFactory.newInstance().newXPath();

            Node node = (Node) xPath.compile(queryExpression).evaluate(document, XPathConstants.NODE);
            if(node == null) {
                System.out.println("No data found!!");
            }

            return node;
        }else{
            NodeList doctor_nodelist = document.getElementsByTagName("doctor");
            NodeList doctor_name_nodelist = document.getElementsByTagName("name");
            for(int x=0,x_flag=doctor_nodelist.getLength(); x<x_flag; x++) {
                String query_d_id = doctor_nodelist.item(x).getAttributes().getNamedItem("d_id").getNodeValue();
                String name = doctor_name_nodelist.item(x).getTextContent();
                System.out.println("D_ID: " + query_d_id + ", Name: " + name);
            }
        }return null;
    }

    @Override
    public Document updateDoctorProfile(Document document, String doctorId, String updateElement ,String updateData) throws XPathExpressionException {
        String query_expression = "/profile/doctor[@d_id='"+ doctorId +"']/"+ updateElement;

        XPath xPath = XPathFactory.newInstance().newXPath();
        Element departmentElement = (Element) xPath.compile(query_expression).evaluate(document, XPathConstants.NODE);

        if (departmentElement != null) {
            departmentElement.setTextContent(updateData);
        }

        return document;
    }


}
