package code.kliangh.xml.controller;

import code.kliangh.xml.service.*;
import code.kliangh.xml.utils.XmlReader;
import code.kliangh.xml.utils.XmlUtils;
import code.kliangh.xml.utils.XmlWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class clinicManagementSystem {
    private static final String DOCTOR_PROFILE = "./src/main/resources/xml_file_system/doctorProfiles.xml";
    private static final String MEDICAL_RECORD = "./src/main/resources/xml_file_system/medicalRecords.xml";

    private static Document doctorProfiles;
    private static Document medicalRecords;

    public static void CommanLineController(String[] args) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException, TransformerException {

        String functionSelect;
        String queryOption;
        String newDataOption;
        String updateOption;
        String terminateFlag = "0";

        XmlReader xmlReader = new XmlReader();
        XmlWriter xmlWriter = new XmlWriter();

        DoctorService doctorService = new DoctorServiceImpl();
        PatientService patientService = new PatientServiceImpl();
        MedicalRecordService medicalRecordService = new MedicalRecordServiceImpl();

        doctorProfiles = xmlReader.readXML(DOCTOR_PROFILE);
        medicalRecords = xmlReader.readXML(MEDICAL_RECORD);

        InputStreamReader ISR = new InputStreamReader(System.in);
        BufferedReader BR = new BufferedReader(ISR);

        while(terminateFlag.equals("0")){
            System.out.println("Please select the function? ");
            System.out.println("1->Query, 2->New Data, 3->Update, 4->Delete Record, 5->Print XML Content, 6->Convert XML to HTML, 7->Save to XML, 0-> exit");
            functionSelect = BR.readLine();
            while(functionSelect.length() == 0){
                System.out.println("Please choose one option:");
                functionSelect = BR.readLine();
            }
            //Top menu, select function
            switch (functionSelect) {

                //Function
                case "1":
                    System.out.println("Please select what you want to Query?");
                    System.out.println("1->Doctor, 2->Patient, 3->Patient medical records, 0->Back to function select menu");
                    queryOption = BR.readLine();
                    while(queryOption.length() == 0){
                        System.out.println("Please choose one option:");
                        queryOption = BR.readLine();
                    }
                    //Query select menu
                    switch (queryOption) {
                        case "0":
                            System.out.println("Going back to function select...");
                            break;
                        case "1":
                            System.out.println("Please give doctor ID a.k.a. D_ID(leave blank to query all): ");
                            String doctorId = BR.readLine();
                            if(doctorId.length() == 0)
                                doctorId = null;
                            Node doctor = doctorService.getDoctor(doctorProfiles, doctorId);
                            if(doctor != null)
                                XmlUtils.showNodeData(doctor);
                            break;
                        case "2":
                            System.out.println("Please give patient ID a.k.a. P_ID(leave blank to querry all): ");
                            String patientId = BR.readLine();
                            if(patientId.length() == 0)
                                patientId = null;
                            Node patient = patientService.getPatient(medicalRecords, patientId);
                            if(patient != null)
                                XmlUtils.showNodeData(patient);
                            break;
                        case "3":
                            System.out.println("Please give Patient ID, Record ID: ");

                            String p_id_case3 = null;
                            String r_id_case3 = null;

                            p_id_case3 = BR.readLine();
                            while(p_id_case3.length() == 0){
                                System.out.println("Patient ID is required!! Please give Patient ID:");
                                p_id_case3 = BR.readLine();
                            }

                            System.out.println("Please give Record ID:");
                            r_id_case3 = BR.readLine();
                            while(r_id_case3.length() == 0 ){
                                System.out.println("Please give Record ID:");
                                r_id_case3 = BR.readLine();
                            }

                            Node queryRecord = medicalRecordService.getRecord(medicalRecords, p_id_case3, r_id_case3);
                            if(queryRecord != null)
                                XmlUtils.showNodeData(queryRecord);
                            break;
                    }
                        break;
                    //Create new data
                    case "2":
                        System.out.println("Please select which new data you are going to insert? ");
                        System.out.println("1->Doctor, 2->Patient, 3->Patient medical records, 0->Back to function select menu");
                        newDataOption = BR.readLine();
                        while(newDataOption.length() == 0){
                            System.out.println("Please choose one option:");
                            newDataOption = BR.readLine();
                        }
                        //Create new data menu
                        switch(newDataOption){
                            case "0":
                                System.out.println("Going back to function select...");
                                break;
                            case "1":
                                System.out.println("To create new Doctor data please insert name and Department.");
                                System.out.println("Name: ");
                                String new_doctor_name = BR.readLine();
                                while(new_doctor_name.length() == 0 ){
                                    System.out.println("Please give doctor name:");
                                    new_doctor_name = BR.readLine();
                                }
                                System.out.println("Department: ");
                                String new_doctor_department = BR.readLine();
                                while(new_doctor_department.length() == 0 ){
                                    System.out.println("Please give doctor department:");
                                    new_doctor_department = BR.readLine();
                                }
                                doctorService.newDoctor(doctorProfiles, new_doctor_name, new_doctor_department);
                                break;
                            case "2":
                                System.out.println("To create new Patient data please insert name.");
                                System.out.println("Name: ");
                                String new_patient_name = BR.readLine();
                                while(new_patient_name.length() == 0 ){
                                    System.out.println("Please give Patient name:");
                                    new_patient_name = BR.readLine();
                                }
                                patientService.newPatient(medicalRecords, new_patient_name);
                                break;
                            case "3":
                                System.out.println("To create new Record please Patient ID and Doctor ID firtst.");
                                System.out.println("Patient ID: ");
                                String new_record_p_id = BR.readLine();
                                while(new_record_p_id.length() == 0 ){
                                    System.out.println("Please give Patient ID name:");
                                    new_record_p_id = BR.readLine();
                                }
                                System.out.println("Doctor ID: ");
                                String new_record_d_id = BR.readLine();
                                while(new_record_d_id.length() == 0 ){
                                    System.out.println("Please give Doctor ID:");
                                    new_record_d_id = BR.readLine();
                                }

                                HashMap<String, String> medicalRecord = new HashMap<String, String>();
                                System.out.println("Second insert Description, Diagnosis, Treatment and Prescription.");
                                System.out.println("Description: ");
                                String new_record_description = BR.readLine();
                                while(new_record_description.length() == 0 ){
                                    System.out.println("Please give description:");
                                    new_record_description = BR.readLine();
                                }medicalRecord.put("description", new_record_description);

                                System.out.println("Diagnosis: ");
                                String new_record_diagnosis = BR.readLine();
                                while(new_record_diagnosis.length() == 0 ){
                                    System.out.println("Please give diagnosis:");
                                    new_record_diagnosis = BR.readLine();
                                }medicalRecord.put("diagnosis", new_record_diagnosis);

                                System.out.println("Treatment: ");
                                String new_record_treatment = BR.readLine();
                                while(new_record_treatment.length() == 0 ){
                                    System.out.println("Please give treatment:");
                                    new_record_treatment = BR.readLine();
                                }medicalRecord.put("treatment", new_record_treatment);

                                System.out.println("Prescription: ");
                                String new_record_prescription = BR.readLine();
                                while(new_record_prescription.length() == 0 ){
                                    System.out.println("Please give prescription:");
                                    new_record_prescription = BR.readLine();
                                }medicalRecord.put("prescription", new_record_prescription);

                                medicalRecordService.addRecord(medicalRecords, new_record_p_id, new_record_d_id, medicalRecord);
                        }
                        break;

                    //Update data
                    case "3":
                        System.out.println("What data you want to update? ");
                        System.out.println("1->Doctor profile, 2->Patient name, 3->Patient medical records, 0-> Back to function select menu");
                        updateOption = BR.readLine();
                        while(updateOption.length() == 0){
                            System.out.println("Please choose one option:");
                            updateOption = BR.readLine();
                        }
                        //Update data menu
                        switch(updateOption){
                            case "0":
                                System.out.println("Going back to function select...");
                                break;
                            case "1":
                                System.out.println("Which doctor profile you want to update? ");
                                System.out.println("Please give Doctor id: ");
                                String update_d_id = BR.readLine();
                                while(update_d_id.length() == 0){
                                    System.out.println("Please insert Doctor id:");
                                    update_d_id = BR.readLine();
                                }

                                System.out.println("What data you want to update?(name or department)");
                                String update_doctor_element = BR.readLine();
                                while(update_doctor_element.equals("name")  && update_doctor_element.equals("department")){
                                    System.out.println("The data is not exist(name or department):");
                                    update_doctor_element = BR.readLine();
                                }

                                System.out.println("New data for the name or department: ");
                                String update_doctor_element_data = BR.readLine();
                                while(update_doctor_element_data.length() == 0){
                                    System.out.println("Please insert data: ");
                                    update_doctor_element_data = BR.readLine();
                                }
                                doctorService.updateDoctorProfile(doctorProfiles, update_d_id, update_doctor_element, update_doctor_element_data);
                            case "2":
                                System.out.println("Which Patient's name you want to change? Please give Patient id: ");
                                String update_patient_name_p_id = BR.readLine();
                                while(update_patient_name_p_id.length() == 0){
                                    System.out.println("Please insert Patient id:");
                                    update_patient_name_p_id = BR.readLine();
                                }

                                System.out.println("Insert new name: ");
                                String update_patient_new_name = BR.readLine();
                                while(update_patient_new_name.length() == 0){
                                    System.out.println("Please insert new name: ");
                                    update_patient_new_name = BR.readLine();
                                }
                                patientService.updatePatientName(medicalRecords, update_patient_name_p_id, update_patient_new_name);

                            case "3":
                                System.out.println("Which record of Patient you want to update? ");
                                System.out.println("Please give Patient id: ");
                                String update_record_p_id = BR.readLine();
                                while(update_record_p_id.length() == 0){
                                    System.out.println("Please insert Patient ID:");
                                    update_record_p_id = BR.readLine();
                                }

                                System.out.println("Which record you want to update?");
                                System.out.println("Please give Record id: ");
                                String update_record_r_id = BR.readLine();
                                while(update_record_r_id.length() == 0){
                                    System.out.println("Please insert Record ID:");
                                    update_record_r_id = BR.readLine();
                                }

                                System.out.println("What data you want to update?(description, diagnosis, treatment or prescription)");
                                String update_record_element = BR.readLine();
                                while(!update_record_element.equals("description") && !update_record_element.equals("diagnosis") && !update_record_element.equals("treatment")  && !update_record_element.equals("prescription")){
                                    System.out.println("The data is not exist(description, diagnosis, treatment or prescription):");
                                    update_record_element = BR.readLine();
                                }

                                System.out.println("New data for description, diagnosis, treatment or prescription: ");
                                String update_record_element_data = BR.readLine();
                                while(update_record_element_data.length() == 0){
                                    System.out.println("Please insert new data:");
                                    update_record_element_data = BR.readLine();
                                }
                                medicalRecordService.updateRecord(medicalRecords, update_record_p_id, update_record_r_id, update_record_element, update_record_element_data);
                        }
                        break;

                    //Delete medical record
                    case "4":
                        System.out.println("Which record of Patient you want to delete? ");
                        System.out.println("Please give Patient id: ");
                        String delete_record_p_id = BR.readLine();
                        while(delete_record_p_id.length() == 0){
                            System.out.println("Please insert Patient ID:");
                            delete_record_p_id = BR.readLine();
                        }

                        System.out.println("Which record you want to delete? ");
                        System.out.println("Please give Record id: ");
                        String delete_record_r_id = BR.readLine();
                        while(delete_record_r_id.length() == 0){
                            System.out.println("Please insert Record ID:");
                            delete_record_r_id = BR.readLine();
                        }
                        medicalRecordService.deleteRecord(medicalRecords, delete_record_p_id, delete_record_r_id);
                        break;

                    //Show XML file content
                    case "5":
                        System.out.println("Show which XML file? 1->doctorProfiles.xml or 2->medicalRecords.xml: ");
                        String show_xml_file = BR.readLine();
                        while(show_xml_file.length() == 0 && !show_xml_file.equals("1") && !show_xml_file.equals("2")){
                            System.out.println("Please insert xml file name:");
                            show_xml_file = BR.readLine();
                        }
                        if(show_xml_file.equals("1"))
                            XmlUtils.printXMLTree(doctorProfiles);
                        else
                            XmlUtils.printXMLTree(medicalRecords);
                        break;

                    //Convert XML file to HTML file
                    case "6":
                        XmlUtils.convertXMLtoHTML("medicalRecords.xml", "medicalRecords.xsl", "medicalRecords.html");
                        System.out.println("Output Finished!!");
                        break;

                    //Save to XML
                    case "7":
                        xmlWriter.saveToXML(doctorProfiles, "./src/main/resources/xml_file_system/doctor_profile_dup.xml", "./src/main/resources/xml_file_system/doctorProfiles.dtd");
                        xmlWriter.saveToXML(medicalRecords, "./src/main/resources/xml_file_system/medical_record_dup.xml", "./src/main/resources/xml_file_system/medicalRecords.dtd");
                        break;

                    //Exit
                    case "0":
                        terminateFlag = "1";
                        System.out.println("Bye Bye! Have a nice day");
                        break;
                }

            }
            BR.close();
            ISR.close();

    }

}
