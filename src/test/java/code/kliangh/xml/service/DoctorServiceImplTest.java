package code.kliangh.xml.service;

import code.kliangh.xml.utils.XmlReader;
import code.kliangh.xml.utils.XmlUtils;
import code.kliangh.xml.utils.XmlWriter;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

class DoctorServiceImplTest {

    private static final String DOCTOR_PROFILE = "./src/main/resources/xml_file_system/doctor_profile.xml";

    private XmlReader xmlReader = new XmlReader();
    private XmlWriter xmlWriter = new XmlWriter();

    private Document doctorProfile;

    private DoctorService doctorService = new DoctorServiceImpl();

    String testDoctorId = "3";
    String testDoctorName = "Test";
    String testDoctorDepartment = "Test Department";

    @BeforeEach
    void setUp() {
        try {
            doctorProfile = xmlReader.readXML(DOCTOR_PROFILE);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void newDoctor() {
        doctorProfile = doctorService.newDoctor(doctorProfile, testDoctorName, testDoctorDepartment);
        assertNotNull(doctorProfile);
    }

    @Test
    void getDoctor() {
        Node doctor = null;
        try {
            doctor = doctorService.getDoctor(doctorProfile, "1");
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        assertNotNull(doctor);

        try {
            doctor = doctorService.getDoctor(doctorProfile, null);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        assertNull(doctor);
    }

    @Test
    void updateDoctorProfile() {
        Node newDoctor = null;

        try {
            doctorProfile = doctorService.newDoctor(doctorProfile, testDoctorName, testDoctorDepartment);
            doctorProfile = doctorService.updateDoctorProfile(doctorProfile, testDoctorId, "name", "testName1");
            newDoctor = doctorService.getDoctor(doctorProfile, testDoctorId);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        newDoctor.getNodeValue();
    }

}