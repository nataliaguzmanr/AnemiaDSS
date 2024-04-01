package ui;


import diagnosis.*;
import jdbc.*;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.InputException;
import utilities.ReadExcel;
import utilities.Utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static utilities.InputException.*;
import static utilities.InputException.getFloatSympTRUE;

public class MedicalStaffMenu {



    private static JDBCManager jdbcManager = new JDBCManager();
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static JDBCUserManager userManager = new JDBCUserManager(jdbcManager);
    private final static JDBCPatientManager jdbcPatientManager = new JDBCPatientManager(jdbcManager);
    private final static JDBCMedicalStaffManager jdbcMedicalStaffManager = new JDBCMedicalStaffManager(jdbcManager);
    private final static JDBCSymptomManager jdbcSymptomManager = new JDBCSymptomManager(jdbcManager);
    private final static JDBCReportManager jdbcReportManager = new JDBCReportManager(jdbcManager);

    private final static JDBCClinicalHistoryManager jdbcClinicalHistoryManager =
            new JDBCClinicalHistoryManager(jdbcManager);
    private static MedicalStaff medStaff = null;
    private static Patient patient = null;


    private static Anemia anemicSyndromeMALE = initializeAnemia(AnemiaType.ANEMIC_SYNDROME_MALE);
    private static Anemia anemicSyndromeFEMALE = initializeAnemia(AnemiaType.ANEMIC_SYNDROME_FEMALE);

    private static Anemia posthemorrhagicMALE = initializeAnemia(AnemiaType.POSTHEMORRHAGIC_ANEMIA_MALE);
    private static Anemia posthemorrhagicFEMALE = initializeAnemia(AnemiaType.POSTHEMORRHAGIC_ANEMIA_FEMALE);

    private static Anemia hemolyticMALE = initializeAnemia(AnemiaType.HEMOLYTIC_ANEMIA_MALE);
    private static Anemia hemolyticFEMALE = initializeAnemia(AnemiaType.HEMOLYTIC_ANEMIA_FEMALE);

    private static Anemia inheritedSpherocytMALE = initializeAnemia(AnemiaType.INHERITED_SPHEROCYTOSIS_MALE);
    private static Anemia inheritedSpherocytFEMALE = initializeAnemia(AnemiaType.INHERITED_SPHEROCYTOSIS_FEMALE);

    private static Anemia thalassemiaMALE = initializeAnemia(AnemiaType.THALASSEMIA_MALE);
    private static Anemia thalassemiaFEMALE = initializeAnemia(AnemiaType.THALASSEMIA_FEMALE);

    private static Anemia ironDeficiencyMALE = initializeAnemia(AnemiaType.IRON_DEFICIENCY_ANEMIA_MALE);
    private static Anemia ironDeficiencyFEMALE = initializeAnemia(AnemiaType.IRON_DEFICIENCY_ANEMIA_FEMALE);

    private static Anemia megaloblasticMALE = initializeAnemia(AnemiaType.MEGALOBLASTIC_ANEMIA_MALE);
    private static Anemia megaloblasticFEMALE = initializeAnemia(AnemiaType.MEGALOBLASTIC_ANEMIA_FEMALE);

    private static Anemia aplasticMALE = initializeAnemia(AnemiaType.APLASTIC_ANEMIA_MALE);
    private static Anemia aplasticFEMALE = initializeAnemia(AnemiaType.APLASTIC_ANEMIA_FEMALE);

    private static Anemia chronicDiseaseMALE = initializeAnemia(AnemiaType.CHRONIC_DISEASE_ANEMIA_MALE);
    private static Anemia chronicDiseaseFEMALE = initializeAnemia(AnemiaType.CHRONIC_DISEASE_ANEMIA_FEMALE);

    private static Anemia polycythemiaMALE = initializeAnemia(AnemiaType.POLYCYTHEMIA_MALE);
    private static Anemia polycythemiaFEMALE = initializeAnemia(AnemiaType.POLYCYTHEMIA_FEMALE);


    public static void main(String[] args) throws Exception{
        welcomeMenu();
    }

    public static Anemia initializeAnemia(AnemiaType anemiaType){

        List<Float> weightsList = ReadExcel.readWeights(anemiaType);
        List<Condition> conditionList = ReadExcel.readConditions(anemiaType);
        Anemia anemia = new Anemia(anemiaType,weightsList,conditionList);

        return anemia;
    }

    // -------------------------------------------------------------------------------------------

    public static void welcomeMenu(){
        try {

            Integer choice;
            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("0. Exit");

                choice = Utilities.readIntFromKeyboardInRange("Option: ", 0, 2);


                switch (choice) {
                    case 1:
                        register();
                        break;
                    case 2:
                        login();
                        break;
                    case 0:
                        System.out.println("Data base closed");
                        jdbcManager.disconnect();
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

public static void register(){
        try {

            String userName = getString("Please, write your USER NAME:");
            String password = getString("Please write your password:");

            boolean userRepeated = userManager.userNameTaken(userName);

            if(userRepeated == true) {
                System.out.println("ERROR, exiting user");
            }else {
                User user = new User(userName, password);
                userManager.newUser(user);
                System.out.println("User created successfully!");
                int user_id = userManager.getUserId(user);

                //System.out.println(user_id);
                //now, we create the MedicalStaff
                String name = InputException.getString("Write your name:");
                medStaff = new MedicalStaff(user_id, name);
                jdbcMedicalStaffManager.addMedicalStaff(medStaff, user_id);

            }
        }catch(Exception e) {
            System.out.println(e.getCause());
        }
    }


public static void login() {
        try{
            String userName = getString("Please, write your USER NAME:");
            String password = getString("Please, write your password:");
            User user = userManager.checkPassword(userName, password);

            if (user == null) {
                System.out.println("Wrong email or password");
                welcomeMenu();
            }else{
                int user_id = user.getId();
                //hacer un get de la informacion del medical staff
                medStaff = jdbcMedicalStaffManager.getMedicalStaff(user_id);
                System.out.println(medStaff);
                System.out.println("Has entrado correctamente");
                patientHandlerMenu();
            }


        } catch(Exception e) {
        System.out.println(e.getCause());
        }

    }

//-------------------------------------------------------------------------------------------
public static void patientHandlerMenu(){
    try {

        Integer choice;
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Research for anemia information");
            System.out.println("2. Diagnose a patient");
            System.out.println("0. Exit application");

            choice = Utilities.readIntFromKeyboardInRange("Option: ", 0, 2);


            switch (choice) {
                case 1:
                    getInfoAnemias();
                    break;

                case 2:
                    patientDiagnosis();
                    break;

                case 0:
                    System.exit(0);
                    break;

            }
        }
    }catch (Exception e) {
        e.printStackTrace();
    }
}


// -------------------------------------------------------------------------------------------

    public static void patientDiagnosis(){

        //this menu has several steps:
        //1 introduce the patient: can be new or already inserted into the database
        //2 introduce the symptoms
        //3 read a report
        //4 see historial


        System.out.println("SELECTION OF THE PATIENT");
        System.out.println("1) Introduce new patient");
        System.out.println("2) Select one of my patients");
        int choice = Utilities.readIntFromKeyboardInRange("Option: ", 1, 2);

        switch (choice) {
            case 1: //introduce a new patient to the database
                patient = addNewPatient();
                //no tiene id asignado
                patient.setId(jdbcPatientManager.getPatientId(patient.getName()));
                break;
            case 2: //select one of the already inserted patients
                patient = selectPatient();
                //no tiene id asignado
                patient.setId(jdbcPatientManager.getPatientId(patient.getName()));
                break;
            default:
                break;
        }

        //new menu to work with the selected patient
        while (true) {

            System.out.println("DIAGNOSIS OF THE PATIENT");

            System.out.println("1) Introduce the symptoms");
            System.out.println("2) Read last report");
            System.out.println("3) Read previous report");
            System.out.println("4) See clinical histories");
            System.out.println("0) Back");

            choice = Utilities.readIntFromKeyboardInRange("Option: ", 0, 3);

            switch (choice) {
                case 1:
                    introduceSymptoms();
                    //this method creates the clinical history, introduces the symptoms, applies the rules and generates the report
                    break;

                case 2:
                    readLastReport();
                    break;

                case 3:
                    readPreviousReport();
                    break;

                case 4:
                    seeClinicalHistories();
                    break;

                case 0:
                    return;
            }
        }

    }


    public static Patient addNewPatient(){

        Patient patient = null;

        System.out.println("INSERTION OF A NEW PATIENT");
        String name = InputException.getString("\nName: ");
        Gender gender = null;

        while(true) {
            String genderStr = InputException.getString("\nGender: insert MALE or FEMALE");
                        if (genderStr.equalsIgnoreCase("MALE")) {
                gender = Gender.MALE;
                break;
            } else if (genderStr.equalsIgnoreCase("FEMALE")) {
                gender = Gender.FEMALE;
                break;
            } else {
                System.out.println("Please, insert MALE or FEMALE as gender");
            }
        }

        int age = InputException.getInt("\nAge: ");
        float weight = InputException.getFloat("\nWeight: ");

        patient = new Patient(name, age, gender, weight);
        System.out.println(patient);
        jdbcPatientManager.addPatient(patient);
        return patient;
    }

    public static Patient selectPatient() {
        // can select all the patients of the database.
        List<Patient> patients = jdbcPatientManager.getAllPatients();

        if(patients.isEmpty()){
            System.out.println("There are no patients in our database");
            Patient p = addNewPatient();
            return p;
        }

        for (Patient p: patients){
            System.out.println("Id: " + p.getId() + ", Name: " + p.getName());
        }
        while (true) {
            int id = InputException.getInt("Introduce the id of the patient to diagnose:\n");
            Patient patient = jdbcPatientManager.getPatient(id);
            System.out.println(patient);
            if (patient == null) {
                System.out.println("Sorry, that patient is not available");
            }
            else {
                return patient;
            }
        }
    }

    public static void introduceSymptoms() {

        try {
            //add symptoms
            //una vez seleccionado el paciente con el que vamos a trabajar, creamos el cuadro clinico e insertamos los sintomas
            ClinicalHistory clinicalHistory = new ClinicalHistory(LocalDate.now());
            jdbcClinicalHistoryManager.addClinicalHistory(clinicalHistory, patient.getId());

            //get Id of the clinical history
            ClinicalHistory ch = jdbcClinicalHistoryManager.getClinicalHistory(patient.getId());

            clinicalHistory.setId(ch.getId());
            System.out.println("\nIntroduce patient symptoms: ");
            System.out.println("------ Press X to skip the field");


            Float valueF = getFloatSymptom("Hemoglobin:");
            Symptom hb = new Symptom(valueF, "Hb");
            patient.addSymptom(hb);
            jdbcSymptomManager.addSymptom(hb, clinicalHistory.getId());

            valueF = getFloatSymptom("Erythropoietin:");
            Symptom epo = new Symptom(valueF, "EPO");
            patient.addSymptom(epo);
            jdbcSymptomManager.addSymptom(epo, clinicalHistory.getId());

            valueF = getFloatSymptom("Hematocrit/PVM:");
            Symptom pvm = new Symptom(valueF, "Hematocrit/PVM");
            patient.addSymptom(pvm);
            jdbcSymptomManager.addSymptom(pvm, clinicalHistory.getId());

            valueF = getFloatSymptom("Platelets:");
            Symptom plaq = new Symptom(valueF, "Platelets");
            patient.addSymptom(plaq);
            jdbcSymptomManager.addSymptom(plaq, clinicalHistory.getId());

            valueF = getFloatSymptom("Anisocytosis coefficient:");
            Symptom anisoc_coeff = new Symptom(valueF, "Anisocytosis coefficient");
            patient.addSymptom(anisoc_coeff);
            jdbcSymptomManager.addSymptom(anisoc_coeff, clinicalHistory.getId());

            valueF = getFloatSymptom("Reticulocytes:");
            Symptom ret = new Symptom(valueF, "Reticulocytes");
            patient.addSymptom(ret);
            jdbcSymptomManager.addSymptom(ret, clinicalHistory.getId());

            valueF = getFloatSymptom("Leukocytes:");
            Symptom leuk = new Symptom(valueF, "Leukocytes");
            patient.addSymptom(leuk);
            jdbcSymptomManager.addSymptom(leuk, clinicalHistory.getId());


            Float normalBloodVol = patient.getWeight() * 0.075F;
            valueF = null;
            if (normalBloodVol > 6) {
                Symptom incBloodVol = new Symptom(1.0F, "Increased blood volume");
                patient.addSymptom(incBloodVol);
                jdbcSymptomManager.addSymptom(incBloodVol, clinicalHistory.getId());

                Symptom decBloodVol = new Symptom(valueF, "Decreased blood volume");
                patient.addSymptom(decBloodVol);
                jdbcSymptomManager.addSymptom(decBloodVol, clinicalHistory.getId());
            } else if (normalBloodVol < 4) {

                Symptom incBloodVol = new Symptom(valueF, "Increased blood volume");
                patient.addSymptom(incBloodVol);
                jdbcSymptomManager.addSymptom(incBloodVol, clinicalHistory.getId());

                Symptom decBloodVol = new Symptom(1.0F, "Decreased blood volume");
                patient.addSymptom(decBloodVol);
                jdbcSymptomManager.addSymptom(decBloodVol, clinicalHistory.getId());
            }else{
                Symptom incBloodVol = new Symptom(null, "Increased blood volume");
                patient.addSymptom(incBloodVol);
                jdbcSymptomManager.addSymptom(incBloodVol, clinicalHistory.getId());

                Symptom decBloodVol = new Symptom(null, "Decreased blood volume");
                patient.addSymptom(decBloodVol);
                jdbcSymptomManager.addSymptom(decBloodVol, clinicalHistory.getId());

            }

            valueF = getFloatSymptom("RBC:");
            Symptom rbc = new Symptom(valueF, "RBC");
            patient.addSymptom(rbc);
            jdbcSymptomManager.addSymptom(rbc, clinicalHistory.getId());

            valueF = getFloatSymptom("VCM:");
            Symptom vch = new Symptom(valueF, "VCM");
            patient.addSymptom(vch);
            jdbcSymptomManager.addSymptom(vch, clinicalHistory.getId());

            valueF = getFloatSymptom("MCH:");
            Symptom mch = new Symptom(valueF, "MCH");
            patient.addSymptom(mch);
            jdbcSymptomManager.addSymptom(mch, clinicalHistory.getId());

            valueF = getFloatSymptom("MCHC:");
            Symptom mhch = new Symptom(valueF, "MCHC");
            patient.addSymptom(mhch);
            jdbcSymptomManager.addSymptom(mhch, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Hemoglobin in urine (YES: 1 / NO: 0):");
            Symptom hbUrine = new Symptom(valueF, "Hemoglobin in urine");
            patient.addSymptom(hbUrine);
            jdbcSymptomManager.addSymptom(hbUrine, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Fe in urine (YES: 1 / NO: 0):");
            Symptom feUrine = new Symptom(valueF, "Fe in urine");
            patient.addSymptom(feUrine);
            jdbcSymptomManager.addSymptom(feUrine, clinicalHistory.getId());

            valueF = getFloatSymptom("Fe:");
            Symptom fe = new Symptom(valueF, "Fe");
            patient.addSymptom(fe);
            jdbcSymptomManager.addSymptom(fe, clinicalHistory.getId());

            valueF = getFloatSymptom("Bilirubin:");
            Symptom bill = new Symptom(valueF, "Bilirubin");
            patient.addSymptom(bill);
            jdbcSymptomManager.addSymptom(bill, clinicalHistory.getId());

            valueF = getFloatSymptom("LDH:");
            Symptom ldh = new Symptom(valueF, "LDH");
            patient.addSymptom(ldh);
            jdbcSymptomManager.addSymptom(ldh, clinicalHistory.getId());

            valueF = getFloatSymptom("Haptoglobin:");
            Symptom haptog = new Symptom(valueF, "Haptoglobin");
            patient.addSymptom(haptog);
            jdbcSymptomManager.addSymptom(haptog, clinicalHistory.getId());

            valueF = getFloatSymptom("Ferritin:");
            Symptom ferr = new Symptom(valueF, "Ferritin");
            patient.addSymptom(ferr);
            jdbcSymptomManager.addSymptom(ferr, clinicalHistory.getId());

            valueF = getFloatSymptom("B12:");
            Symptom b12 = new Symptom(valueF, "B12");
            patient.addSymptom(b12);
            jdbcSymptomManager.addSymptom(b12, clinicalHistory.getId());

            valueF = getFloatSymptom("Folic acid:");
            Symptom folAc = new Symptom(valueF, "Folic acid");
            patient.addSymptom(folAc);
            jdbcSymptomManager.addSymptom(folAc, clinicalHistory.getId());

            valueF = getFloatSymptom("Glucocorticoids:");
            Symptom gluc = new Symptom(valueF, "Glucocorticoids");
            patient.addSymptom(gluc);
            jdbcSymptomManager.addSymptom(gluc, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Skin paleness:");
            Symptom skinPal = new Symptom(valueF, "Skin paleness");
            patient.addSymptom(skinPal);
            jdbcSymptomManager.addSymptom(skinPal, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Tachycardia");
            Symptom tach = new Symptom(valueF, "Tachycardia");
            patient.addSymptom(tach);
            jdbcSymptomManager.addSymptom(tach, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Tinnitus");
            Symptom tinn = new Symptom(valueF, "Tinnitus");
            patient.addSymptom(tinn);
            jdbcSymptomManager.addSymptom(tinn, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Chest pain:");
            Symptom chestPain = new Symptom(valueF, "Chest pain");
            patient.addSymptom(chestPain);
            jdbcSymptomManager.addSymptom(chestPain, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Infarction:");
            Symptom infar = new Symptom(valueF, "Infarction");
            patient.addSymptom(infar);
            jdbcSymptomManager.addSymptom(infar, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Dizziness:");
            Symptom dizz = new Symptom(valueF, "Dizziness");
            patient.addSymptom(dizz);
            jdbcSymptomManager.addSymptom(dizz, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Blurred vision:");
            Symptom blurredV = new Symptom(valueF, "Blurred vision");
            patient.addSymptom(blurredV);
            jdbcSymptomManager.addSymptom(blurredV, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Headache:");
            Symptom head = new Symptom(valueF, "Headache");
            patient.addSymptom(head);
            jdbcSymptomManager.addSymptom(head, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Fatigue:");
            Symptom fat = new Symptom(valueF, "Fatigue");
            patient.addSymptom(fat);
            jdbcSymptomManager.addSymptom(fat, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Muscle pain:");
            Symptom muscleP = new Symptom(valueF, "Muscle pain");
            patient.addSymptom(muscleP);
            jdbcSymptomManager.addSymptom(muscleP, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Dyspnea:");
            Symptom dysp = new Symptom(valueF, "Dyspnea");
            patient.addSymptom(dysp);
            jdbcSymptomManager.addSymptom(dysp, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Tachypnea:");
            Symptom tachp = new Symptom(valueF, "Tachypnea");
            patient.addSymptom(tachp);
            jdbcSymptomManager.addSymptom(tachp, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Cold hands/feet:");
            Symptom coldHF = new Symptom(valueF, "Cold hands/feet");
            patient.addSymptom(coldHF);
            jdbcSymptomManager.addSymptom(coldHF, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Intern hemorrhage:");
            Symptom intHem = new Symptom(valueF, "Intern hemorrhage");
            patient.addSymptom(intHem);
            jdbcSymptomManager.addSymptom(intHem, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Extern hemorrhage:");
            Symptom extHem = new Symptom(valueF, "Extern hemorrhage");
            patient.addSymptom(extHem);
            jdbcSymptomManager.addSymptom(extHem, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Nausea, poor apetite:");
            Symptom nau = new Symptom(valueF, "Nausea, poor apetite");
            patient.addSymptom(nau);
            jdbcSymptomManager.addSymptom(nau, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Jaundice:");
            Symptom jaun = new Symptom(valueF, "Jaundice");
            patient.addSymptom(jaun);
            jdbcSymptomManager.addSymptom(jaun, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Splenomegaly:");
            Symptom sple = new Symptom(valueF, "Splenomegaly");
            patient.addSymptom(sple);
            jdbcSymptomManager.addSymptom(sple, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Cranial ballooning:");
            Symptom cran = new Symptom(valueF, "Cranial ballooning");
            patient.addSymptom(cran);
            jdbcSymptomManager.addSymptom(cran, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Aplastic crisis:");
            Symptom aplC = new Symptom(valueF, "Aplastic crisis");
            patient.addSymptom(aplC);
            jdbcSymptomManager.addSymptom(aplC, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Bacterial infections:");
            Symptom bactInf = new Symptom(valueF, "Bacterial infections");
            patient.addSymptom(bactInf);
            jdbcSymptomManager.addSymptom(bactInf, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Osteoporosis:");
            Symptom ost = new Symptom(valueF, "Osteoporosis");
            patient.addSymptom(ost);
            jdbcSymptomManager.addSymptom(ost, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Bone deformations:");
            Symptom boneDef = new Symptom(valueF, "Bone deformations");
            patient.addSymptom(boneDef);
            jdbcSymptomManager.addSymptom(boneDef, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Maxila bone hyperplasia:");
            Symptom maxHyp = new Symptom(valueF, "Maxila bone hyperplasia");
            patient.addSymptom(maxHyp);
            jdbcSymptomManager.addSymptom(maxHyp, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Brittle nails:");
            Symptom britN = new Symptom(valueF, "Brittle nails");
            patient.addSymptom(britN);
            jdbcSymptomManager.addSymptom(britN, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Tongue inflammation:");
            Symptom tong = new Symptom(valueF, "Tongue inflammation");
            patient.addSymptom(tong);
            jdbcSymptomManager.addSymptom(tong, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Diarrhea:");
            Symptom diar = new Symptom(valueF, "Diarrhea");
            patient.addSymptom(diar);
            jdbcSymptomManager.addSymptom(diar, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Paresthesia:");
            Symptom par = new Symptom(valueF, "Paresthesia");
            patient.addSymptom(par);
            jdbcSymptomManager.addSymptom(par, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Cyanosis:");
            Symptom cya = new Symptom(valueF, "Cyanosis");
            patient.addSymptom(cya);
            jdbcSymptomManager.addSymptom(cya, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Thrombosis:");
            Symptom thr = new Symptom(valueF, "Thrombosis");
            patient.addSymptom(thr);
            jdbcSymptomManager.addSymptom(thr, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Vomit:");
            Symptom vom = new Symptom(valueF, "Vomit");
            patient.addSymptom(vom);
            jdbcSymptomManager.addSymptom(vom, clinicalHistory.getId());

            valueF = getFloatSympTRUE("Petechiae:");
            Symptom pet = new Symptom(valueF, "Petechiae");
            patient.addSymptom(pet);
            jdbcSymptomManager.addSymptom(pet, clinicalHistory.getId());


            PatientUnit patientUnit;
            RuleUnitInstance<PatientUnit> instance;

            final Logger LOG = LoggerFactory.getLogger(MedicalStaffMenu.class);

            LOG.info("Creating RuleUnit");
            patientUnit = new PatientUnit();

            instance = RuleUnitProvider.get().createRuleUnitInstance(patientUnit);

            LOG.info("Insert data");
            patientUnit.getPatients().add(patient);

            LOG.info("Fire rules");
            instance.fire();


            instance.close();


            List<Anemia> anemiasList = patient.getAnemiasList();
            List<Symptom> symptomsList = patient.getSymptomsList();

            List<Float> scoresList = new LinkedList<>();

            for(Anemia a : anemiasList) {

                //tenemos que inicializar las conditions y weights
                AnemiaType type = a.getAnemiaType();
                switch (type) {
                    case ANEMIC_SYNDROME_MALE:
                        a.setWeights(anemicSyndromeMALE.getWeights());
                        a.setConditions(anemicSyndromeMALE.getConditions());
                        break;
                    case ANEMIC_SYNDROME_FEMALE:
                        a.setWeights(anemicSyndromeFEMALE.getWeights());
                        a.setConditions(anemicSyndromeFEMALE.getConditions());
                        break;
                    case POSTHEMORRHAGIC_ANEMIA_MALE:
                        a.setWeights(posthemorrhagicMALE.getWeights());
                        a.setConditions(posthemorrhagicMALE.getConditions());
                        break;
                    case POSTHEMORRHAGIC_ANEMIA_FEMALE:
                        a.setWeights(posthemorrhagicFEMALE.getWeights());
                        a.setConditions(posthemorrhagicFEMALE.getConditions());
                        break;
                    case HEMOLYTIC_ANEMIA_MALE:
                        a.setWeights(hemolyticMALE.getWeights());
                        a.setConditions(hemolyticMALE.getConditions());
                        break;
                    case HEMOLYTIC_ANEMIA_FEMALE:
                        a.setWeights(hemolyticFEMALE.getWeights());
                        a.setConditions(hemolyticFEMALE.getConditions());
                        break;
                    case INHERITED_SPHEROCYTOSIS_MALE:
                        a.setWeights(inheritedSpherocytMALE.getWeights());
                        a.setConditions(inheritedSpherocytMALE.getConditions());
                        break;
                    case INHERITED_SPHEROCYTOSIS_FEMALE:
                        a.setWeights(inheritedSpherocytFEMALE.getWeights());
                        a.setConditions(inheritedSpherocytFEMALE.getConditions());
                        break;
                    case THALASSEMIA_MALE:
                        a.setWeights(thalassemiaMALE.getWeights());
                        a.setConditions(thalassemiaMALE.getConditions());
                        break;
                    case THALASSEMIA_FEMALE:
                        a.setWeights(thalassemiaFEMALE.getWeights());
                        a.setConditions(thalassemiaFEMALE.getConditions());
                        break;
                    case IRON_DEFICIENCY_ANEMIA_MALE:
                        a.setWeights(ironDeficiencyMALE.getWeights());
                        a.setConditions(ironDeficiencyMALE.getConditions());
                        break;
                    case IRON_DEFICIENCY_ANEMIA_FEMALE:
                        a.setWeights(ironDeficiencyFEMALE.getWeights());
                        a.setConditions(ironDeficiencyFEMALE.getConditions());
                        break;
                    case MEGALOBLASTIC_ANEMIA_MALE:
                        a.setWeights(megaloblasticMALE.getWeights());
                        a.setConditions(megaloblasticMALE.getConditions());
                        break;
                    case MEGALOBLASTIC_ANEMIA_FEMALE:
                        a.setWeights(megaloblasticFEMALE.getWeights());
                        a.setConditions(megaloblasticFEMALE.getConditions());
                        break;
                    case APLASTIC_ANEMIA_MALE:
                        a.setWeights(aplasticMALE.getWeights());
                        a.setConditions(aplasticMALE.getConditions());
                        break;
                    case APLASTIC_ANEMIA_FEMALE:
                        a.setWeights(aplasticFEMALE.getWeights());
                        a.setConditions(aplasticFEMALE.getConditions());
                        break;
                    case CHRONIC_DISEASE_ANEMIA_MALE:
                        a.setWeights(chronicDiseaseMALE.getWeights());
                        a.setConditions(chronicDiseaseMALE.getConditions());
                        break;
                    case CHRONIC_DISEASE_ANEMIA_FEMALE:
                        a.setWeights(chronicDiseaseFEMALE.getWeights());
                        a.setConditions(chronicDiseaseFEMALE.getConditions());
                        break;
                    case POLYCYTHEMIA_MALE:
                        a.setWeights(polycythemiaMALE.getWeights());
                        a.setConditions(polycythemiaMALE.getConditions());
                        break;
                    case POLYCYTHEMIA_FEMALE:
                        a.setWeights(polycythemiaFEMALE.getWeights());
                        a.setConditions(polycythemiaFEMALE.getConditions());
                        break;
                }
            }

            //calculamos los scores para cada anemia
            for(Anemia a : anemiasList) {
                List<Condition> conditionList = a.getConditions();
                //System.out.println(conditionList);
                List<Boolean> booleanList = Utilities.equalsSymptomCondition(symptomsList, conditionList);
                //System.out.println(booleanList);
                //System.out.println(symptomsList);
                Float score = Utilities.getScore(a,booleanList);
                System.out.println(score);
                scoresList.add(score);
            }

            //write the report
            String r = Utilities.getReport(medStaff, clinicalHistory, patient, scoresList, anemiasList);
            //TODO añadir el report a la base de datos
            Report report = new Report(r, patient);
            jdbcReportManager.addReport(report);

        }catch(SQLException e){
            System.out.println(e.getCause());
        }
    }

    public static void readLastReport(){
        Report r = jdbcReportManager.getReport(patient);
        System.out.println("This is the file you need to open");
        System.out.println(r.getFile_name());

    }


    public static void readPreviousReport(){
        List<Report> reports = jdbcReportManager.getReportsList(patient);
        System.out.println("These are the reports that this patient has:");
        for (Report r : reports){
            System.out.println(r.getFile_name());
        }
    }


    public static void seeClinicalHistories(){
        List<ClinicalHistory> histories = jdbcClinicalHistoryManager.getHistoriesList(patient);
        List<Integer> available_ids = new LinkedList<>();
        for (ClinicalHistory h : histories){
            System.out.println("Id: " + h.getId() + " Date: " + h.getSymptomsDate().toString());
            available_ids.add(h.getId());
        }

        int id = InputException.getInt("Introduce the id of the history so you can see the symptoms: ");
        if(available_ids.contains(id)) {
            List<Symptom> symptoms = jdbcSymptomManager.getSymptomsList(id);
            for (Symptom s : symptoms) {
                System.out.println(s);
            }
        }else{
            System.out.println("There is no history with that Id");
            return;
        }
    }


    public static void getInfoAnemias(){
        System.out.println("Below there are several resources you can visit to gain knowledge about anemias: ");

        System.out.println("GENERAL PATHOLOGY: ");
        System.out.println("Pérez Arellano JL. Manual de patología general. 7ª edición, pp 404-405 (ISBN: 978-8445822166)");
        System.out.println("Pastrana Delgado J, García de Casasola Sánchez. Fisiopatología y patología general básicas para ciencias de la salud, pp 65-68. (ISBN: 9788480869461).");
        System.out.println("Laso Guzmán FJ. Introducción a la medicina clínica. 3ª edición, pp 279-235. (ISBN: 9788445826065).");
        System.out.println();

        System.out.println("ANEMIC SYNDROME:");
        System.out.println("Borrego Gutiérrez MJ (2020), Fisiopatología de los eritrocitos.  Fisiopatología. Universidad CEU San Pablo.");
        System.out.println();

        System.out.println("POSTHEMORRHAGIC ANEMIA:");
        System.out.println("McGrath JP. Assessment of Hemolytic and Hemorrhagic Anemias in Preclinical Safety Assessment Studies. Department of Clinical Pathology, Toxicology Research Laboratories, Greenfield Indiana, 1993");
        System.out.println("Acute anemia. National Library of Medicine. (NIH)  https://www.ncbi.nlm.nih.gov/books/NBK537232/ ");
        System.out.println();

        System.out.println("HEMOLYTIC ANEMIA:");
        System.out.println("Anemia hemolítica. National Heart, Lung and Blood Institute (NIH) https://www.ncbi.nlm.nih.gov/books/NBK537232/ ");
        System.out.println();

        System.out.println("INHERITED SPHEROCYTOSIS:");
        System.out.println("Martín-Consuegra S, Sebastián E, Salinas JA. Guía esferocitosis hereditaria. Diagnóstico y manejo en población pedriátrica. Sociedad Española de Hematología y Oncología pediátricas SEHOP https://www.sehop.org/wp-content/uploads/2022/04/GUIA-ESFEROCITOSIS-HEREDITARIA-SEHOP.pdf ");
        System.out.println();

        System.out.println("THALASSEMIA:");
        System.out.println("¿Qué es la talasemia? Centros para el Control y la Prevención de Enfermedades CDC. https://www.cdc.gov/ncbddd/spanish/thalassemia/facts.html ");
        System.out.println();

        System.out.println("IRON-DEFICIENCY ANEMIA:");
        System.out.println("Iron-Deficiency anemia. National Heart, Lung and Blood Institute (NIH) https://www.nhlbi.nih.gov/health/anemia/iron-deficiency-anemia#How-is-iron-deficiency-anemia-diagnosed ");
        System.out.println("Iron deficiency anemia. https://www.mayoclinic.org/diseases-conditions/iron-deficiency-anemia/symptoms-causes/syc-20355034  ");
        System.out.println("Iron-deficiency anemia. Cleveland Clinic.  https://my.clevelandclinic.org/health/diseases/22824-iron-deficiency-anemia");
        System.out.println();

        System.out.println("MEGALOBLASTIC ANEMIA:");
        System.out.println("Borrego Gutiérrez MJ (2020), Fisiopatología de los eritrocitos.  Fisiopatología. Universidad CEU San Pablo.");
        System.out.println();

        System.out.println("APLASTIC ANEMIA:");
        System.out.println("Borrego Gutiérrez MJ (2020), Fisiopatología de los eritrocitos.  Fisiopatología. Universidad CEU San Pablo.");
        System.out.println();

        System.out.println("CHRONIC DISEASE ANEMIA");
        System.out.println("Anemia por enfermedad crónica. MedlinePlus. https://medlineplus.gov/spanish/ency/article/000565.htm ");
        System.out.println();

        System.out.println("POLYCYTHEMIA");
        System.out.println("Borrego Gutiérrez MJ (2020), Fisiopatología de los eritrocitos.  Fisiopatología. Universidad CEU San Pablo.");
        System.out.println("Pérez Arellano JL. Manual de patología general. 7ª edición, pp 404-405 (ISBN: 978-8445822166).");

        System.out.println();
        String read  = InputException.getString("Insert x to go back:");
        if (read.equalsIgnoreCase( "x")){
            return;
        }
    }


}






