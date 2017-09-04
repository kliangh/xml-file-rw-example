package code.kliangh.xml.service;

import code.kliangh.xml.utils.XmlReader;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import static org.junit.Assert.*;

public class PatientServiceImplTest {

    private static final String MEDICAL_RECORD = "./src/main/resources/xml_file_system/medical_record.xml";

    private XmlReader xmlReader = new XmlReader();

    private Document medicalRecord;

    private PatientService patientService = new PatientServiceImpl();

    String testPatientId = "2";
    String testPatientName = "Test";

    @Before
    public void setUp() throws Exception {
        medicalRecord = xmlReader.readXML(MEDICAL_RECORD);
    }

    @Test
    public void newPatient() throws Exception {
        medicalRecord = patientService.newPatient(medicalRecord, testPatientName);
        assertNotNull(medicalRecord);
    }

    @Test
    public void getPatient() throws Exception {
        Node patient = patientService.getPatient(medicalRecord, "1");
        assertNotNull(patient);

        patient = patientService.getPatient(medicalRecord, null);
        assertNull(patient);
    }

    @Test
    public void updatePatientName() throws Exception {
        medicalRecord = patientService.newPatient(medicalRecord, testPatientName);
        medicalRecord = patientService.updatePatientName(medicalRecord, testPatientId, "TestPatient");

        Node newPatient = patientService.getPatient(medicalRecord, testPatientId);
        String updatedPatientName = newPatient.getAttributes().getNamedItem("name").getNodeValue();

        assertEquals(updatedPatientName, "TestPatient");
    }

}