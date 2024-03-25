package ui;

import POJOS.ClinicalHistory;
import POJOS.Gender;
import POJOS.Patient;
import POJOS.Symptom;
import jdbc.JDBCClinicalHistoryManager;
import jdbc.JDBCManager;
import jdbc.JDBCPatientManager;
import jdbc.JDBCSymptomManager;
import jpa.JPAUserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;

import static utilities.InputException.getFloat;

public class PatientHandlerMenu {

    private static JDBCManager jdbcManager = new JDBCManager();
    private static JDBCPatientManager jdbcPatientManager = new JDBCPatientManager(jdbcManager);
    private static JDBCSymptomManager jdbcSymptomManager = new JDBCSymptomManager(jdbcManager);
    private static JDBCClinicalHistoryManager jdbcClinicalHistoryManager =
            new JDBCClinicalHistoryManager(jdbcManager);


    private static BufferedReader bufferedReadereader = new BufferedReader(new InputStreamReader(System.in));
    private static JPAUserManager jpaUserManager = new JPAUserManager();

    public static void patientHandlerMenu(){

        System.out.println("Choose an option:");
        System.out.println("1. Research for anemia information");
        System.out.println("2. Diagnose a patient");
        System.out.println("0. Back");

        int choice = 0;
        try {
            choice = Integer.parseInt(bufferedReadereader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        switch (choice) {
            case 1:
                //
                //break;

            case 2:
                patientDiagnosis();
                break;

        }
    }

    public static void patientDiagnosis(){

        System.out.println("Introduce the id of the patient to diagnose:\n");
        try {
            String id_str = bufferedReadereader.readLine();
            int id = Integer.parseInt(id_str);
            Patient patient = jdbcPatientManager.getPatient(id);

            if (patient==null){

                System.out.println("Patient not found in database, please, insert it");
                System.out.println("\nName: ");
                String name = bufferedReadereader.readLine();

                System.out.println("\nGender: insert MALE or FEMALE");
                String genderStr = bufferedReadereader.readLine();

                Gender gender =null;
                if(genderStr.equalsIgnoreCase("MALE")){
                   gender = Gender.MALE;
                }else if (genderStr.equalsIgnoreCase("FEMALE")){
                    gender = Gender.FEMALE;
                }else{
                    System.out.println("Please, insert MALE or FEMALE as gender");
                }

                System.out.println("\nAge: ");
                String ageStr = bufferedReadereader.readLine();
                int age = Integer.parseInt(ageStr);

                System.out.println("\nWeight: ");
                String weightStr = bufferedReadereader.readLine();
                int weight = Integer.parseInt(weightStr);

                patient = new Patient(name, age, gender, weight);
                jdbcPatientManager.addPatient(patient);

            }else{

                ClinicalHistory clinicalHistory = new ClinicalHistory(LocalDate.now());
                jdbcClinicalHistoryManager.addClinicalHistory(clinicalHistory, patient.getId());

                System.out.println("\nIntroduce patient symptoms: ");
                System.out.println("------ Press intro to skip the field");


                Float valueF = getFloat("Hemoglobin:");
                Symptom hb = new Symptom(valueF, "Hb");
                jdbcSymptomManager.addSymptom(hb, );

                valueF = getFloat("Erythropoietin");
                Symptom epo = new Symptom(valueF, "EPO");

                valueF = getFloat("Erythropoietin");
                Symptom epo = new Symptom(valueF, "EPO");









            }



        } catch (IOException e) {
            System.out.println(e.getCause());
        }catch (SQLException e) {
            System.out.println(e.getCause());
        }




    }
}
