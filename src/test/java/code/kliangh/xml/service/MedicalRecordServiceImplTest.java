package code.kliangh.xml.service;

import code.kliangh.xml.utils.XmlReader;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MedicalRecordServiceImplTest {
    private static final String DOCTOR_PROFILE = "./src/test/resources/xml_file_system/doctor_profile.xml";
    private static final String MEDICAL_RECORD = "./src/test/resources/xml_file_system/medical_record.xml";

    private XmlReader xmlReader = new XmlReader();

    private Document doctorProfiles;
    private Document medicalRecords;

    private DoctorService doctorService;
    private MedicalRecordService medicalRecordService;

    private HashMap<String, String> medicalRecord;

    private String testDoctorId = "1";
    private String testPatientId = "1";
    private String testDescription = "Test Description";
    private String testDiagnosis = "Psychopath";
    private String testTreatment = "Hang to die";
    private String testPrescription = "Cocaine";

    @Before
    public void setUp() throws Exception {
        doctorProfiles = xmlReader.readXML(DOCTOR_PROFILE);
        medicalRecords = xmlReader.readXML(MEDICAL_RECORD);

        doctorService = new DoctorServiceImpl();
        medicalRecordService = new MedicalRecordServiceImpl();

        medicalRecord = new HashMap<>();
        medicalRecord.put("description", testDescription);
        medicalRecord.put("diagnosis", testDiagnosis);
        medicalRecord.put("treatment", testTreatment);
        medicalRecord.put("prescription", testPrescription);
    }

    @Test
    public void addRecord() throws Exception {
        medicalRecords = medicalRecordService.addRecord(medicalRecords, testPatientId, testDoctorId, medicalRecord);

        assertNotNull(medicalRecords);
    }

    @Test
    public void getRecord() throws Exception {
        Node queryRecord = medicalRecordService.getRecord(medicalRecords, "1", "1");

        assertNotNull(queryRecord);
        assertTrue(queryRecord instanceof Node);
    }

    @Test
    public void updateRecord() throws Exception {
        medicalRecords = medicalRecordService.addRecord(medicalRecords, testPatientId, testDoctorId, medicalRecord);
        medicalRecords = medicalRecordService.updateRecord(medicalRecords, "1", "3", "diagnosis", "Retarded");

        Node newMedicalRecord = medicalRecordService.getRecord(medicalRecords, "1", "3");
        NodeList nodes = newMedicalRecord.getFirstChild().getNextSibling().getChildNodes();

        assertEquals(nodes.item(0).getNodeValue(), "Retarded");
    }

    @Test
    public void deleteRecord() throws Exception {
        medicalRecords = medicalRecordService.addRecord(medicalRecords, testPatientId, testDoctorId, medicalRecord);
        Node newMedicalRecord = medicalRecordService.getRecord(medicalRecords, "1", "3");
        assertNotNull(newMedicalRecord);

        medicalRecords = medicalRecordService.deleteRecord(medicalRecords, "1", "3");
        newMedicalRecord = medicalRecordService.getRecord(medicalRecords, "1", "3");
        assertNull(newMedicalRecord);

    }

}