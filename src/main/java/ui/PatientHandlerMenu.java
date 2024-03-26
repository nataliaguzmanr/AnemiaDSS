package ui;

import POJOS.*;
import jdbc.JDBCClinicalHistoryManager;
import jdbc.JDBCManager;
import jdbc.JDBCPatientManager;
import jdbc.JDBCSymptomManager;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.InputException;
import utilities.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static utilities.InputException.*;


public class PatientHandlerMenu {

    private final static JDBCManager jdbcManager = new JDBCManager();
    private final static JDBCPatientManager jdbcPatientManager = new JDBCPatientManager(jdbcManager);
    private final static JDBCSymptomManager jdbcSymptomManager = new JDBCSymptomManager(jdbcManager);
    private final static JDBCClinicalHistoryManager jdbcClinicalHistoryManager =
            new JDBCClinicalHistoryManager(jdbcManager);
    private final static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));



    public static void patientHandlerMenu(){

        System.out.println("Choose an option:");
        System.out.println("1. Research for anemia information");
        System.out.println("2. Diagnose a patient");
        System.out.println("0. Exit application");

        int choice = 0;
        try {
            choice = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        switch (choice) {
            case 1:
                //
                break;

            case 2:
                patientDiagnosis();
                break;

            case 0:
                System.exit(0);
                break;


        }
    }

    public static void patientDiagnosis(){

        try {

            int id = InputException.getInt("Introduce the id of the patient to diagnose:\n");
            Patient patient = jdbcPatientManager.getPatient(id);
            System.out.println(patient);

            if (patient == null){

                System.out.println("Patient not found in database, please, insert it");
                String name = InputException.getString("\nName: ");

                String genderStr = InputException.getString("\nGender: insert MALE or FEMALE");

                Gender gender =null;
                if(genderStr.equalsIgnoreCase("MALE")){
                   gender = Gender.MALE;
                }else if (genderStr.equalsIgnoreCase("FEMALE")){
                    gender = Gender.FEMALE;
                }else{
                    System.out.println("Please, insert MALE or FEMALE as gender");
                }

                int age = InputException.getInt("\nAge: ");

                float weight = InputException.getFloat("\nWeight: ");

                patient = new Patient(name, age, gender, weight);
                System.out.println(patient);
                jdbcPatientManager.addPatient(patient);

            }

            ClinicalHistory clinicalHistory = new ClinicalHistory(LocalDate.now());
            jdbcClinicalHistoryManager.addClinicalHistory(clinicalHistory, patient.getId());

            System.out.println("\nIntroduce patient symptoms: ");
            System.out.println("------ Press X to skip the field");


            Float valueF = getFloatSymptom("Hemoglobin:");
            Symptom hb = new Symptom(valueF, "Hb");
            patient.addSymptom(hb);
            jdbcSymptomManager.addSymptom(hb,clinicalHistory.getId());

            valueF = getFloatSymptom("Erythropoietin:");
            Symptom epo = new Symptom(valueF, "EPO");
            patient.addSymptom(epo);
            jdbcSymptomManager.addSymptom(epo,clinicalHistory.getId());

            valueF = getFloatSymptom("Hematocrit/PVM:");
            Symptom pvm = new Symptom(valueF, "PVM");
            patient.addSymptom(pvm);
            jdbcSymptomManager.addSymptom(pvm,clinicalHistory.getId());

            valueF = getFloatSymptom("Plaquets:");
            Symptom plaq = new Symptom(valueF, "Plaquets");
            patient.addSymptom(plaq);
            jdbcSymptomManager.addSymptom(plaq,clinicalHistory.getId());

            valueF = getFloatSymptom("Anisocytosis coefficient:");
            Symptom anisoc_coeff = new Symptom(valueF, "Anisocytosis coefficient");
            patient.addSymptom(anisoc_coeff);
            jdbcSymptomManager.addSymptom(anisoc_coeff,clinicalHistory.getId());

            valueF = getFloatSymptom("Reticulocytes:");
            Symptom ret = new Symptom(valueF, "Reticulocytes");
            patient.addSymptom(ret);
            jdbcSymptomManager.addSymptom(ret,clinicalHistory.getId());

            float normalBloodVol = patient.getWeight()*0.075F;
            valueF = null;
            if (normalBloodVol>6){
                Symptom incBloodVol = new Symptom(1.0F, "Increased blood volume");
                patient.addSymptom(incBloodVol);
                jdbcSymptomManager.addSymptom(incBloodVol,clinicalHistory.getId());

                Symptom decBloodVol = new Symptom(valueF, "Decreased blood volume");
                patient.addSymptom(decBloodVol);
                jdbcSymptomManager.addSymptom(decBloodVol,clinicalHistory.getId());
            } else if (normalBloodVol<4) {

                Symptom incBloodVol = new Symptom(valueF, "Increased blood volume");
                patient.addSymptom(incBloodVol);
                jdbcSymptomManager.addSymptom(incBloodVol,clinicalHistory.getId());

                Symptom decBloodVol = new Symptom(1.0F, "Decreased blood volume");
                patient.addSymptom(decBloodVol);
                jdbcSymptomManager.addSymptom(decBloodVol,clinicalHistory.getId());
            }

            valueF = getFloatSymptom("RBC:");
            Symptom rbc = new Symptom(valueF, "RBC");
            patient.addSymptom(rbc);
            jdbcSymptomManager.addSymptom(rbc,clinicalHistory.getId());

            valueF = getFloatSymptom("VCH:");
            Symptom vch = new Symptom(valueF, "VCH");
            patient.addSymptom(vch);
            jdbcSymptomManager.addSymptom(vch,clinicalHistory.getId());

            valueF = getFloatSymptom("MCH:");
            Symptom mch = new Symptom(valueF, "MCH");
            patient.addSymptom(mch);
            jdbcSymptomManager.addSymptom(mch,clinicalHistory.getId());

            valueF = getFloatSymptom("MHCH:");
            Symptom mhch = new Symptom(valueF, "MHCH");
            patient.addSymptom(mhch);
            jdbcSymptomManager.addSymptom(mhch,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Hemoglobin in urine (YES: 1 / NO: 0):");
            Symptom hbUrine = new Symptom(valueF, "Hemoglobin in urine");
            patient.addSymptom(hbUrine);
            jdbcSymptomManager.addSymptom(hbUrine,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Fe in urine (YES: 1 / NO: 0):");
            Symptom feUrine = new Symptom(valueF, "Fe in urine");
            patient.addSymptom(feUrine);
            jdbcSymptomManager.addSymptom(feUrine,clinicalHistory.getId());

            valueF = getFloatSymptom("Fe:");
            Symptom fe = new Symptom(valueF, "Fe");
            patient.addSymptom(fe);
            jdbcSymptomManager.addSymptom(fe,clinicalHistory.getId());

            valueF = getFloatSymptom("Bilirrubine:");
            Symptom bill = new Symptom(valueF, "Bilirrubine");
            patient.addSymptom(bill);
            jdbcSymptomManager.addSymptom(bill,clinicalHistory.getId());

            valueF = getFloatSymptom("LDH:");
            Symptom ldh = new Symptom(valueF, "LDH");
            patient.addSymptom(ldh);
            jdbcSymptomManager.addSymptom(ldh,clinicalHistory.getId());

            valueF = getFloatSymptom("Haptoglobin:");
            Symptom haptog = new Symptom(valueF, "Haptoglobin");
            patient.addSymptom(haptog);
            jdbcSymptomManager.addSymptom(haptog,clinicalHistory.getId());

            valueF = getFloatSymptom("Ferritin:");
            Symptom ferr = new Symptom(valueF, "Ferritin");
            patient.addSymptom(ferr);
            jdbcSymptomManager.addSymptom(ferr,clinicalHistory.getId());

            valueF = getFloatSymptom("B12:");
            Symptom b12 = new Symptom(valueF, "B12");
            patient.addSymptom(b12);
            jdbcSymptomManager.addSymptom(b12,clinicalHistory.getId());

            valueF = getFloatSymptom("Folic acid:");
            Symptom folAc = new Symptom(valueF, "Folic acid");
            patient.addSymptom(folAc);
            jdbcSymptomManager.addSymptom(folAc,clinicalHistory.getId());

            valueF = getFloatSymptom("Glucocorticoids:");
            Symptom gluc = new Symptom(valueF, "Glucocorticoids");
            patient.addSymptom(gluc);
            jdbcSymptomManager.addSymptom(gluc,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Skin paleness:");
            Symptom skinPal = new Symptom(valueF, "Skin paleness");
            patient.addSymptom(skinPal);
            jdbcSymptomManager.addSymptom(skinPal,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Tachycardia");
            Symptom tach = new Symptom(valueF, "Tachycardia");
            patient.addSymptom(tach);
            jdbcSymptomManager.addSymptom(tach,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Tinnitus");
            Symptom tinn = new Symptom(valueF, "Tinnitus");
            patient.addSymptom(tinn);
            jdbcSymptomManager.addSymptom(tinn,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Chest pain:");
            Symptom chestPain = new Symptom(valueF, "Chest pain");
            patient.addSymptom(chestPain);
            jdbcSymptomManager.addSymptom(chestPain,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Infarction:");
            Symptom infar = new Symptom(valueF, "Infarction");
            patient.addSymptom(infar);
            jdbcSymptomManager.addSymptom(infar,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Dizziness:");
            Symptom dizz = new Symptom(valueF, "Dizziness");
            patient.addSymptom(dizz);
            jdbcSymptomManager.addSymptom(dizz,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Blurred vision:");
            Symptom blurredV = new Symptom(valueF, "Blurred vision");
            patient.addSymptom(blurredV);
            jdbcSymptomManager.addSymptom(blurredV,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Headache:");
            Symptom head = new Symptom(valueF, "Headache");
            patient.addSymptom(head);
            jdbcSymptomManager.addSymptom(head,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Fatigue:");
            Symptom fat = new Symptom(valueF, "Fatigue");
            patient.addSymptom(fat);
            jdbcSymptomManager.addSymptom(fat,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Muscle pain:");
            Symptom muscleP = new Symptom(valueF, "Muscle pain");
            patient.addSymptom(muscleP);
            jdbcSymptomManager.addSymptom(muscleP,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Dyspnea:");
            Symptom dysp = new Symptom(valueF, "Dyspnea");
            patient.addSymptom(dysp);
            jdbcSymptomManager.addSymptom(dysp,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Tachypnea:");
            Symptom tachp = new Symptom(valueF, "Tachypnea");
            patient.addSymptom(tachp);
            jdbcSymptomManager.addSymptom(tachp,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Cold hands or feet:");
            Symptom coldHF = new Symptom(valueF, "Cold hands or feet");
            patient.addSymptom(coldHF);
            jdbcSymptomManager.addSymptom(coldHF,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Intern hemorrhage:");
            Symptom intHem = new Symptom(valueF, "Intern hemorrhaget");
            patient.addSymptom(intHem);
            jdbcSymptomManager.addSymptom(intHem,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Extern hemorrhage:");
            Symptom extHem = new Symptom(valueF, "Intern hemorrhaget");
            patient.addSymptom(extHem);
            jdbcSymptomManager.addSymptom(extHem,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Naussea or poor apetite:");
            Symptom nau = new Symptom(valueF, "Naussea or poor apetite");
            patient.addSymptom(nau);
            jdbcSymptomManager.addSymptom(nau,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Jaundice:");
            Symptom jaun = new Symptom(valueF, "Jaundice");
            patient.addSymptom(jaun);
            jdbcSymptomManager.addSymptom(jaun,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Splenomegaly:");
            Symptom sple = new Symptom(valueF, "Splenomegaly");
            patient.addSymptom(sple);
            jdbcSymptomManager.addSymptom(sple,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Craneal ballooning:");
            Symptom cran = new Symptom(valueF, "Craneal ballooning");
            patient.addSymptom(cran);
            jdbcSymptomManager.addSymptom(cran,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Aplasic crisis:");
            Symptom aplC = new Symptom(valueF, "Aplasic crisis");
            patient.addSymptom(aplC);
            jdbcSymptomManager.addSymptom(aplC,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Bacterial infection:");
            Symptom bactInf = new Symptom(valueF, "Bacterial infection");
            patient.addSymptom(bactInf);
            jdbcSymptomManager.addSymptom(bactInf,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Osteoporosis:");
            Symptom ost = new Symptom(valueF, "Osteoporosis");
            patient.addSymptom(ost);
            jdbcSymptomManager.addSymptom(ost,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Bone deformations:");
            Symptom boneDef = new Symptom(valueF, "Bone deformations");
            patient.addSymptom(boneDef);
            jdbcSymptomManager.addSymptom(boneDef,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Maxilar bone hyperplasia:");
            Symptom maxHyp = new Symptom(valueF, "Maxilar bone hyperplasia");
            patient.addSymptom(maxHyp);
            jdbcSymptomManager.addSymptom(maxHyp,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Brittle nails:");
            Symptom britN = new Symptom(valueF, "Brittle nails");
            patient.addSymptom(britN);
            jdbcSymptomManager.addSymptom(britN,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Tongue inflamation:");
            Symptom tong = new Symptom(valueF, "Tongue inflamation");
            patient.addSymptom(tong);
            jdbcSymptomManager.addSymptom(tong,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Diarrhea:");
            Symptom diar = new Symptom(valueF, "Diarrhea");
            patient.addSymptom(diar);
            jdbcSymptomManager.addSymptom(diar,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Paresthesia:");
            Symptom par = new Symptom(valueF, "Paresthesia");
            patient.addSymptom(par);
            jdbcSymptomManager.addSymptom(par,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Cyanosis:");
            Symptom cya = new Symptom(valueF, "Cyanosis");
            patient.addSymptom(cya);
            jdbcSymptomManager.addSymptom(cya,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Thrombosis:");
            Symptom thr = new Symptom(valueF, "Thrombosis");
            patient.addSymptom(thr);
            jdbcSymptomManager.addSymptom(thr,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Vomit:");
            Symptom vom = new Symptom(valueF, "Vomit");
            patient.addSymptom(vom);
            jdbcSymptomManager.addSymptom(vom,clinicalHistory.getId());

            valueF = getFloatSympTRUE("Petechiae:");
            Symptom pet = new Symptom(valueF, "Petechiae");
            patient.addSymptom(pet);
            jdbcSymptomManager.addSymptom(pet,clinicalHistory.getId());

            //FIRE RULES
            final Logger LOG = LoggerFactory.getLogger(PatientHandlerMenu.class);
            PatientUnit patientUnit = new PatientUnit();
            LOG.info("Creating RuleUnit");

            RuleUnitInstance<PatientUnit> instance = RuleUnitProvider.get().createRuleUnitInstance(patientUnit);
            LOG.info("Insert data");

            List<Anemia> anemiasList = patient.getAnemiasList();
            System.out.println(anemiasList);
            List<Symptom> symptomsList = patient.getSymptomsList();
            System.out.println(symptomsList);
            List<Float> scoresList = new LinkedList<>();

            for(int i=0; i<anemiasList.size(); i++){

                List<Condition> conditionList = anemiasList.get(i).getConditions();
                System.out.println(conditionList);
                List<Boolean> booleanList = Utilities.equalsSymptomCondition(symptomsList, conditionList);
                float score = Utilities.getScore(anemiasList.get(i),booleanList);
                scoresList.add(score);

            }

            System.out.println(scoresList);

            //GENERATE REPORT
            Utilities.getReport(MedicalStaffMenu.getMedicalStaff(), clinicalHistory, patient, scoresList,anemiasList);

        } catch (SQLException e) {
            System.out.println(e.getCause());
        }




    }
}
