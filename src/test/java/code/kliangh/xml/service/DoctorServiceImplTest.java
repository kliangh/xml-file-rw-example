package code.kliangh.xml.service;

import code.kliangh.xml.exception.ServiceException;
import code.kliangh.xml.utils.XmlReader;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import static org.junit.Assert.*;

public class DoctorServiceImplTest {

    private static final String DOCTOR_PROFILE = "./src/test/resources/xml_file_system/doctor_profile.xml";

    private XmlReader xmlReader = new XmlReader();

    private Document doctorProfile;

    private DoctorService doctorService = new DoctorServiceImpl();

    String testDoctorId = "3";
    String testDoctorName = "Test";
    String testDoctorDepartment = "Test Department";

    @Before
    public void setUp() throws Exception {
        doctorProfile = xmlReader.readXML(DOCTOR_PROFILE);
    }

    @Test
    public void newDoctor() throws Exception {
        doctorProfile = doctorService.newDoctor(doctorProfile, testDoctorName, testDoctorDepartment);
        assertNotNull(doctorProfile);
    }

    @Test
    public void getDoctor() throws Exception {
        Node doctor = doctorService.getDoctor(doctorProfile, "1");
        assertNotNull(doctor);

        doctor = doctorService.getDoctor(doctorProfile, null);
        assertNull(doctor);
    }

    @Test(expected = ServiceException.class)
    public void getNonExistingDoctor() throws Exception {
        Node doctor = doctorService.getDoctor(doctorProfile, String.valueOf(Integer.MAX_VALUE));
        assertNull(doctor);
    }

    @Test
    public void updateDoctorProfile() throws Exception {
        doctorProfile = doctorService.newDoctor(doctorProfile, testDoctorName, testDoctorDepartment);
        doctorProfile = doctorService.updateDoctorProfile(doctorProfile, testDoctorId, "name", "testName1");

        Node newDoctor = doctorService.getDoctor(doctorProfile, testDoctorId);
        NodeList nodes = newDoctor.getFirstChild().getChildNodes();

        assertEquals(nodes.item(0).getNodeValue(), "testName1");
    }

}