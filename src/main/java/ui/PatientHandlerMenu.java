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

import static utilities.InputException.*;

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

            }

            ClinicalHistory clinicalHistory = new ClinicalHistory(LocalDate.now());
            jdbcClinicalHistoryManager.addClinicalHistory(clinicalHistory, patient.getId());

            System.out.println("\nIntroduce patient symptoms: ");
            System.out.println("------ Press X to skip the field");


            Float valueF = getFloatSymptom("Hemoglobin:");
            Symptom hb = new Symptom(valueF, "Hb");
            jdbcSymptomManager.addSymptom(hb,clinicalHistory.getId());

            valueF = getFloatSymptom("Erythropoietin:");
            Symptom epo = new Symptom(valueF, "EPO");
            jdbcSymptomManager.addSymptom(epo,clinicalHistory.getId());

            valueF = getFloatSymptom("Hematocrit/PVM:");
            Symptom pvm = new Symptom(valueF, "PVM");
            jdbcSymptomManager.addSymptom(pvm,clinicalHistory.getId());

            valueF = getFloatSymptom("Plaquets:");
            Symptom plaquets = new Symptom(valueF, "Plaquets");
            jdbcSymptomManager.addSymptom(plaquets,clinicalHistory.getId());

            valueF = getFloatSymptom("Anisocytosis coefficient:");
            Symptom anisoc_coeff = new Symptom(valueF, "Anisocytosis coefficient");
            jdbcSymptomManager.addSymptom(anisoc_coeff,clinicalHistory.getId());

            valueF = getFloatSymptom("Reticulocytes:");
            Symptom ret = new Symptom(valueF, "Reticulocytes");
            jdbcSymptomManager.addSymptom(ret,clinicalHistory.getId());

            float normalBloodVol = patient.getWeight()*0.075F;
            valueF = null;
            if (normalBloodVol>6){
                Symptom incBloodVol = new Symptom(1.0F, "Increased blood volume");
                jdbcSymptomManager.addSymptom(incBloodVol,clinicalHistory.getId());
                Symptom decBloodVol = new Symptom(valueF, "Decreased blood volume");
                jdbcSymptomManager.addSymptom(decBloodVol,clinicalHistory.getId());
            } else if (normalBloodVol<4) {
                Symptom incBloodVol = new Symptom(valueF, "Increased blood volume");
                jdbcSymptomManager.addSymptom(incBloodVol,clinicalHistory.getId());
                Symptom decBloodVol = new Symptom(1.0F, "Decreased blood volume");
                jdbcSymptomManager.addSymptom(decBloodVol,clinicalHistory.getId());
            }

            valueF = getFloatSymptom("RBC:");
            Symptom rbc = new Symptom(valueF, "RBC");
            jdbcSymptomManager.addSymptom(rbc,clinicalHistory.getId());

            valueF = getFloatSymptom("VCH:");
            Symptom vch = new Symptom(valueF, "VCH");
            jdbcSymptomManager.addSymptom(vch,clinicalHistory.getId());

            valueF = getFloatSymptom("MCH:");
            Symptom mch = new Symptom(valueF, "MCH");
            jdbcSymptomManager.addSymptom(mch,clinicalHistory.getId());

            valueF = getFloatSymptom("MHCH:");
            Symptom mhch = new Symptom(valueF, "MHCH");
            jdbcSymptomManager.addSymptom(mhch,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Hemoglobin in urine (YES: 1 / NO: 0):");
            Symptom hbUrine = new Symptom(valueF, "Hemoglobin in urine");
            jdbcSymptomManager.addSymptom(hbUrine,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Fe in urine (YES: 1 / NO: 0):");
            Symptom feUrine = new Symptom(valueF, "Fe in urine");
            jdbcSymptomManager.addSymptom(feUrine,clinicalHistory.getId());

            valueF = getFloatSymptom("Fe:");
            Symptom fe = new Symptom(valueF, "Fe");
            jdbcSymptomManager.addSymptom(fe,clinicalHistory.getId());

            valueF = getFloatSymptom("Bilirrubine:");
            Symptom bill = new Symptom(valueF, "Bilirrubine");
            jdbcSymptomManager.addSymptom(bill,clinicalHistory.getId());

            valueF = getFloatSymptom("LDH:");
            Symptom ldh = new Symptom(valueF, "LDH");
            jdbcSymptomManager.addSymptom(ldh,clinicalHistory.getId());

            valueF = getFloatSymptom("Haptoglobin:");
            Symptom haptog = new Symptom(valueF, "Haptoglobin");
            jdbcSymptomManager.addSymptom(haptog,clinicalHistory.getId());

            valueF = getFloatSymptom("Ferritin:");
            Symptom ferr = new Symptom(valueF, "Ferritin");
            jdbcSymptomManager.addSymptom(ferr,clinicalHistory.getId());

            valueF = getFloatSymptom("B12:");
            Symptom b12 = new Symptom(valueF, "B12");
            jdbcSymptomManager.addSymptom(b12,clinicalHistory.getId());

            valueF = getFloatSymptom("Folic acid:");
            Symptom folAc = new Symptom(valueF, "Folic acid");
            jdbcSymptomManager.addSymptom(folAc,clinicalHistory.getId());

            valueF = getFloatSymptom("Glucocorticoids:");
            Symptom gluc = new Symptom(valueF, "Glucocorticoids");
            jdbcSymptomManager.addSymptom(gluc,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Skin paleness:");
            Symptom skinPal = new Symptom(valueF, "Skin paleness");
            jdbcSymptomManager.addSymptom(skinPal,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Tachycardia");
            Symptom tach = new Symptom(valueF, "Tachycardia");
            jdbcSymptomManager.addSymptom(tach,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Tinnitus");
            Symptom tinn = new Symptom(valueF, "Tinnitus");
            jdbcSymptomManager.addSymptom(tinn,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Chest pain:");
            Symptom chestPain = new Symptom(valueF, "Chest pain");
            jdbcSymptomManager.addSymptom(chestPain,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Infarction:");
            Symptom infar = new Symptom(valueF, "Infarction");
            jdbcSymptomManager.addSymptom(infar,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Dizziness:");
            Symptom dizz = new Symptom(valueF, "Dizziness");
            jdbcSymptomManager.addSymptom(dizz,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Blurred vision:");
            Symptom blurredV = new Symptom(valueF, "Blurred vision");
            jdbcSymptomManager.addSymptom(blurredV,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Headache:");
            Symptom head = new Symptom(valueF, "Headache");
            jdbcSymptomManager.addSymptom(head,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Fatigue:");
            Symptom fat = new Symptom(valueF, "Fatigue");
            jdbcSymptomManager.addSymptom(fat,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Muscle pain:");
            Symptom muscleP = new Symptom(valueF, "Muscle pain");
            jdbcSymptomManager.addSymptom(muscleP,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Dyspnea:");
            Symptom dysp = new Symptom(valueF, "Dyspnea");
            jdbcSymptomManager.addSymptom(dysp,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Tachypnea:");
            Symptom tachp = new Symptom(valueF, "Tachypnea");
            jdbcSymptomManager.addSymptom(tachp,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Cold hands or feet:");
            Symptom coldHF = new Symptom(valueF, "Cold hands or feet");
            jdbcSymptomManager.addSymptom(coldHF,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Intern hemorrhage:");
            Symptom intHem = new Symptom(valueF, "Intern hemorrhaget");
            jdbcSymptomManager.addSymptom(intHem,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Extern hemorrhage:");
            Symptom extHem = new Symptom(valueF, "Intern hemorrhaget");
            jdbcSymptomManager.addSymptom(extHem,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Naussea or poor apetite:");
            Symptom nau = new Symptom(valueF, "Naussea or poor apetite");
            jdbcSymptomManager.addSymptom(nau,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Jaundice:");
            Symptom jaun = new Symptom(valueF, "Jaundice");
            jdbcSymptomManager.addSymptom(jaun,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Splenomegaly:");
            Symptom sple = new Symptom(valueF, "Splenomegaly");
            jdbcSymptomManager.addSymptom(sple,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Craneal balloning:");
            Symptom cran = new Symptom(valueF, "Craneal balloning");
            jdbcSymptomManager.addSymptom(cran,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Aplasic crisis:");
            Symptom aplC = new Symptom(valueF, "Aplasic crisis");
            jdbcSymptomManager.addSymptom(aplC,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Bacterial infection:");
            Symptom bactInf = new Symptom(valueF, "Bacterial infection");
            jdbcSymptomManager.addSymptom(bactInf,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Osteoporosis:");
            Symptom ost = new Symptom(valueF, "Osteoporosis");
            jdbcSymptomManager.addSymptom(ost,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Bone deformations:");
            Symptom boneDef = new Symptom(valueF, "Bone deformations");
            jdbcSymptomManager.addSymptom(boneDef,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Maxilar bone hyperplasia:");
            Symptom maxHyp = new Symptom(valueF, "Maxilar bone hyperplasia");
            jdbcSymptomManager.addSymptom(maxHyp,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Brittle nails:");
            Symptom britN = new Symptom(valueF, "Brittle nails");
            jdbcSymptomManager.addSymptom(britN,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Tongue inflamation:");
            Symptom tong = new Symptom(valueF, "Tongue inflamation");
            jdbcSymptomManager.addSymptom(tong,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Diarrhea:");
            Symptom diar = new Symptom(valueF, "Diarrhea");
            jdbcSymptomManager.addSymptom(diar,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Paresthesia:");
            Symptom par = new Symptom(valueF, "Paresthesia");
            jdbcSymptomManager.addSymptom(par,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Cyanosis:");
            Symptom cya = new Symptom(valueF, "Cyanosis");
            jdbcSymptomManager.addSymptom(cya,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Thrombosis:");
            Symptom thr = new Symptom(valueF, "Thrombosis");
            jdbcSymptomManager.addSymptom(thr,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Vomit:");
            Symptom vom = new Symptom(valueF, "Vomit");
            jdbcSymptomManager.addSymptom(vom,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Petechiae:");
            Symptom pet = new Symptom(valueF, "Petechiae");
            jdbcSymptomManager.addSymptom(pet,clinicalHistory.getId());




        } catch (IOException e) {
            System.out.println(e.getCause());
        }catch (SQLException e) {
            System.out.println(e.getCause());
        }




    }
}
