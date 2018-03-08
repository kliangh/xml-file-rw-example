package code.kliangh.xml.utils;

import code.kliangh.xml.service.DoctorService;
import code.kliangh.xml.service.DoctorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class XmlUtilsTest {
    private static final String DOCTOR_PROFILE = "./src/test/resources/xml_file_system/doctor_profile.xml";

    private XmlReader xmlReader;

    private Document doctorProfiles;

    private DoctorService doctorService;

    @Before
    public void setUp() throws Exception {
        XmlUtils xmlUtils = new XmlUtils();

        xmlReader = new XmlReader();
        doctorProfiles = xmlReader.readXML(DOCTOR_PROFILE);
        doctorService = new DoctorServiceImpl();
    }

    @Test
    public void printXMLTree() throws Exception {
        XmlUtils.printXMLTree(doctorProfiles);
    }

    @Test
    public void showNodeData() throws Exception {
        Node doctor = doctorService.getDoctor(doctorProfiles, "1");
        XmlUtils.showNodeData(doctor);
    }

    @Test
    public void convertXMLtoHTML() throws Exception {
        XmlUtils.convertXMLtoHTML("./src/test/resources/xml_file_system/doctor_profile.xml", "./src/main/resources/xml_file_system/doctor_profile.xsl", "doctorProfile.html");
    }

}