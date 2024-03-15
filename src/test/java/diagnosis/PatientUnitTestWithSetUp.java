package diagnosis;


import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PatientUnitTestWithSetUp {

    static final Logger LOG = LoggerFactory.getLogger(PatientUnitTestWithSetUp.class);

    PatientUnit patientUnit;
    RuleUnitInstance<PatientUnit> instance;
    Patient pat_posthemorrhagic;
    Patient pat_syndrome;
    Patient pat_hemolytic;
    Patient pat_ironDef;
    Patient pat_megaloblastic;
    Patient pat_megaloblasticB12;
    Patient pat_megaloblasticFolicAc;
    Patient pat_aplastic;
    Patient pat_chronicDiseaseMALE;
    Patient pat_chronicDiseaseFEMALE;


    /**
     * This method will be run before EACH single test. If we want to have common code for all tests we can use @BeforeClass
     * In this case, the creations of the facts (operations) and the insertion in each instance will be common to each test, but they should be
     * run before each test, otherwise the instance will have already trigger/fire its rules so they will not be run apart from the first test if
     * the facts do not change (refractoriness).
     */
    @Before
    public void setUp() {

        LOG.info("Creating RuleUnit");
        patientUnit = new PatientUnit();

        instance = RuleUnitProvider.get().createRuleUnitInstance(patientUnit);
        LOG.info("Insert data");

        //Anemic Syndrome
        pat_syndrome = new Patient("Marta", 18, Gender.FEMALE, LocalDate.of(1987,10,12));
        Symptom hb1 = new Symptom(7.5F, "Hb", pat_syndrome);
        pat_syndrome.addSymptom(hb1);

        //Posthemorragic anemia
        pat_posthemorrhagic = new Patient("Paco", 58, Gender.MALE, LocalDate.of(1987,10,12));
        Symptom hb = new Symptom(9, "Hb", pat_posthemorrhagic);
        Symptom rbcM = new Symptom(4.1F, "RBC", pat_posthemorrhagic);
        Symptom mhch = new Symptom(307F, "MHCH", pat_posthemorrhagic);
        Symptom fe1 = new Symptom(55, "Fe", pat_posthemorrhagic);
        pat_posthemorrhagic.addSymptom(hb);
        pat_posthemorrhagic.addSymptom(rbcM);
        pat_posthemorrhagic.addSymptom(mhch);
        pat_posthemorrhagic.addSymptom(fe1);


        //Hemolytic anemia
        pat_hemolytic = new Patient("Lucia", 58, Gender.FEMALE, LocalDate.of(1987,10,12));
        Symptom mch = new Symptom(40, "MCH", pat_hemolytic);
        Symptom fe2 = new Symptom(200, "MCH", pat_hemolytic);
        Symptom hapto = new Symptom(35, "MCH", pat_hemolytic);
        pat_hemolytic.addSymptom(mch);
        pat_hemolytic.addSymptom(fe2);
        pat_hemolytic.addSymptom(hapto);


        //Iron deficiency anemia
        pat_ironDef = new Patient("Isidoro", 81, Gender.MALE, LocalDate.of(2024, 3,15));
        Symptom mch2 = new Symptom(26, "MCH", pat_ironDef);
        Symptom ferr = new Symptom(10, "Ferritin", pat_ironDef);
        pat_ironDef.addSymptom(mch2);
        pat_ironDef.addSymptom(ferr);

        //Megaloblastic anemia
        pat_megaloblastic = new Patient("Almudena", 29, Gender.FEMALE, LocalDate.of(2024, 3,15));
        Symptom b12 = new Symptom(177, "B12", pat_megaloblastic);
        Symptom folicAc = new Symptom(2.55F, "Folic Acid", pat_megaloblastic);
        pat_megaloblastic.addSymptom(b12);
        pat_megaloblastic.addSymptom(folicAc);
        //Megaloblastic anemia b12
        pat_megaloblasticB12 = new Patient("Lola", 55, Gender.FEMALE, LocalDate.of(2024, 3,15));
        Symptom b12vit = new Symptom(185, "B12", pat_megaloblasticB12);
        pat_megaloblasticB12.addSymptom(b12vit);
        //Megaloblastic anemia Folic Acid
        pat_megaloblasticFolicAc = new Patient("Pepe", 43, Gender.MALE, LocalDate.of(2024, 3,15));
        Symptom fa = new Symptom(2, "B12", pat_megaloblasticFolicAc);
        pat_megaloblasticFolicAc.addSymptom(fa);

        //Aplastic Anemia
        pat_aplastic = new Patient("Natalia", 27,  Gender.FEMALE, LocalDate.of(2024, 3,15));
        Symptom plaq = new Symptom(7, "Plaquets", pat_aplastic);
        Symptom leuk = new Symptom(7, "Leukocytes", pat_aplastic);
        pat_aplastic.addSymptom(plaq);
        pat_aplastic.addSymptom(leuk);

        pat_chronicDiseaseMALE = new Patient("Gustavo", 44,  Gender.MALE, LocalDate.of(2023, 12,22));
        Symptom epo = new Symptom(2.3F, "EPO", pat_chronicDiseaseMALE);
        Symptom ferr1 = new Symptom(310, "Ferritine", pat_chronicDiseaseMALE);
        pat_chronicDiseaseMALE.addSymptom(epo);
        pat_chronicDiseaseMALE.addSymptom(ferr1);

        pat_chronicDiseaseFEMALE = new Patient("Gadea", 44,  Gender.FEMALE, LocalDate.of(2023, 12,22));
        Symptom ferr2 = new Symptom(210, "Ferritine", pat_chronicDiseaseFEMALE);
        pat_chronicDiseaseFEMALE.addSymptom(ferr2);

//---------------------------------------------------------------------------------------

        patientUnit.getPatients().add(pat_posthemorrhagic);
        patientUnit.getPatients().add(pat_syndrome);
        patientUnit.getPatients().add(pat_hemolytic);
        patientUnit.getPatients().add(pat_ironDef);
        patientUnit.getPatients().add(pat_megaloblastic);
        patientUnit.getPatients().add(pat_megaloblasticB12);
        patientUnit.getPatients().add(pat_megaloblasticFolicAc);
        patientUnit.getPatients().add(pat_aplastic);
        patientUnit.getPatients().add(pat_chronicDiseaseMALE);
        patientUnit.getPatients().add(pat_chronicDiseaseFEMALE);

        //System.out.println(patientUnit.getTestString());

//        patientUnit.getPatients().add(p4);
//        patientUnit.getPatients().add(p5);
//        patientUnit.getPatients().add(p6);
//        patientUnit.getPatients().add(p7);

        // instance.close() should not be instantiated because it will be used in each test!
    }


    @Test
    public void testGender() {
        System.out.println("\n---test gender 1Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> genderPat = patientUnit.getPatientsMALE();
            assertEquals(3, patientUnit.getPatientsMALE().size());
            assertTrue(genderPat.contains(pat_posthemorrhagic));
            //System.out.println(patientUnit.getTestString());

        } finally {
            instance.close();
        }
    }
    @Test
    public void testAnemicSyndromeHb_1Expected() {
        System.out.println("testAnemicSyndromeHb1Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithAnemicSyndrome = patientUnit.getPatientsWithAnemicSyndrome();
            assertEquals(1, patientsWithAnemicSyndrome.size());
            assertTrue(patientsWithAnemicSyndrome.contains(pat_syndrome));
            //System.out.println(patientUnit.getTestString());
        } finally {
            instance.close();
        }
    }
    @Test
    public void testPosthemorragicAnemiaRBC_1Expected() {
        System.out.println("testPosthemorragicAnemiaMHCH1Expected");
        try {
            LOG.info("Fire  10. PosthemorragicAnemiaMCHC rule");
            instance.fire();

            Set<Patient> patientsWithPosthemorragicAnemia = patientUnit.getPatientsWithPosthemorrhagicAnemia();
            assertEquals(1, patientUnit.getPatientsWithPosthemorrhagicAnemia().size());
            assertTrue(patientsWithPosthemorragicAnemia.contains(pat_posthemorrhagic));
            //System.out.println(patientUnit.getTestString());
        } finally {
            instance.close();
        }
    }

    @Test
    public void testPosthemorrhagicAnemiaMHCH_1Expected() {
        System.out.println("test Posthemorragic Anemia MHCH 1Expected");
        try {
            LOG.info("Fire  11. PosthemorragicAnemiaFe rule");
            instance.fire();

            Set<Patient> patientsWithPosthemorragicAnemia = patientUnit.getPatientsWithPosthemorrhagicAnemia();
            assertEquals(1, patientUnit.getPatientsWithPosthemorrhagicAnemia().size());
            assertTrue(patientsWithPosthemorragicAnemia.contains(pat_posthemorrhagic));
            //System.out.println(patientUnit.getTestString());
        } finally {
            instance.close();
        }
    }

    @Test
    public void testPosthemorrhagicAnemiaFe_1Expected() {
        System.out.println("test Posthemorragic Anemia Fe 1Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithPosthemorrhagicAnemia = patientUnit.getPatientsWithPosthemorrhagicAnemia();
            assertEquals(1, patientUnit.getPatientsWithPosthemorrhagicAnemia().size());
            assertTrue(patientsWithPosthemorrhagicAnemia.contains(pat_posthemorrhagic));
            //System.out.println(patientUnit.getTestString());

        } finally {
            instance.close();
        }
    }

    @Test
    public void testHemolyticAnemiaMCH_1Expected() {
        System.out.println("\n---test Hemolytic Anemia MCH 1Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithHemolyticAnemia = patientUnit.getPatientsWithHemolyticAnemia();
            assertEquals(1, patientUnit.getPatientsWithHemolyticAnemia().size());
            assertTrue(patientsWithHemolyticAnemia.contains(pat_hemolytic));
            System.out.println(patientUnit.getTestString());

        } finally {
            instance.close();
        }
    }

    @Test
    public void testHemolyticAnemiaFe1_Expected() {

        System.out.println("\n---test Hemolytic Anemia Fe 1Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithHemolyticAnemia = patientUnit.getPatientsWithHemolyticAnemia();
            assertEquals(1, patientUnit.getPatientsWithHemolyticAnemia().size());
            assertTrue(patientsWithHemolyticAnemia.contains(pat_hemolytic));
            System.out.println(patientUnit.getTestString());

        } finally {
            instance.close();
        }
    }

    @Test
    public void testHemolyticAnemiaHaptoglobin_1Expected() {
        System.out.println("\n---test Hemolytic Anemia Haptoglobin 1Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithHemolyticAnemia = patientUnit.getPatientsWithHemolyticAnemia();
            assertEquals(1, patientUnit.getPatientsWithHemolyticAnemia().size());
            assertTrue(patientsWithHemolyticAnemia.contains(pat_hemolytic));

        } finally {
            instance.close();
        }
    }

    @Test
    public void testIronDefAnemiaMCH_1Expected() {
        System.out.println("\n---test Iron deficiency Anemia MCH 1Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithIronDeficiencyAnemia = patientUnit.getPatientsWithIronDeficiencyAnemia();
            assertEquals(1, patientUnit.getPatientsWithIronDeficiencyAnemia().size());
            assertTrue(patientsWithIronDeficiencyAnemia.contains(pat_ironDef));

        } finally {
            instance.close();
        }
    }

    @Test
    public void testIronDefAnemiaFerritin1_Expected() {
        System.out.println("\n---test Iron deficiency Anemia ferritin 1 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithIronDeficiencyAnemia = patientUnit.getPatientsWithIronDeficiencyAnemia();
            assertEquals(1, patientUnit.getPatientsWithIronDeficiencyAnemia().size());
            assertTrue(patientsWithIronDeficiencyAnemia.contains(pat_ironDef));

        } finally {
            instance.close();
        }
    }

    @Test
    public void testMegaloblasticAnemiaB12_1Expected() {
        System.out.println("\n---test Megaloblastic Anemia b12 1 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithMegaloblasticAnemia = patientUnit.getPatientsWithMegaloblasticAnemia();
            assertEquals(1, patientUnit.getPatientsWithMegaloblasticAnemia().size());
            assertTrue(patientsWithMegaloblasticAnemia.contains(pat_megaloblastic));

        } finally {
            instance.close();
        }
    }

    @Test
    public void testMegaloblasticAnemiaFolicAcid_1Expected() {
        System.out.println("\n---test Megaloblastic Anemia Folic Acid 1 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithMegaloblasticAnemia = patientUnit.getPatientsWithMegaloblasticAnemia();
            assertEquals(1, patientUnit.getPatientsWithMegaloblasticAnemia().size());
            assertTrue(patientsWithMegaloblasticAnemia.contains(pat_megaloblastic));

        } finally {
            instance.close();
        }
    }

    @Test
    public void testMegaloblasticAnemiaB12FolicAcid_OR_3Expected() {
        System.out.println("\n---test Megaloblastic Anemia B12 OR Folic Acid 1 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithMegaloblasticAnemia = patientUnit.getPatientsWithMegaloblasticAnemia();
            assertEquals(3, patientUnit.getPatientsWithMegaloblasticAnemia().size());
            assertTrue(patientsWithMegaloblasticAnemia.contains(pat_megaloblasticB12));
            assertTrue(patientsWithMegaloblasticAnemia.contains(pat_megaloblasticFolicAc));
            assertTrue(patientsWithMegaloblasticAnemia.contains(pat_megaloblastic));

        } finally {
            instance.close();
        }
    }

    @Test
    public void testAplasticAnemiaPlaquets_1Expected() {
        System.out.println("\n---test Aplastic Anemia Plaquets 1 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithAplasticAnemia = patientUnit.getPatientsWithAplasticAnemia();
            assertEquals(1, patientUnit.getPatientsWithAplasticAnemia().size());
            assertTrue(patientsWithAplasticAnemia.contains(pat_aplastic));


        } finally {
            instance.close();
        }
    }

    @Test
    public void testAplasticAnemiaLeukocytes_1Expected() {
        System.out.println("\n---test Aplastic Anemia Leukocytes 1 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithAplasticAnemia = patientUnit.getPatientsWithAplasticAnemia();
            assertEquals(1, patientUnit.getPatientsWithAplasticAnemia().size());
            assertTrue(patientsWithAplasticAnemia.contains(pat_aplastic));


        } finally {
            instance.close();
        }
    }

    @Test
    public void testChronicDiseaseAnemiaEPO_1Expected() {
        System.out.println("\n---test Chronic Disease Anemia EPO 1 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithChronicDiseaseAnemia = patientUnit.getPatientsWithChronicDiseaseAnemia();
            assertEquals(1, patientUnit.getPatientsWithChronicDiseaseAnemia().size());
            assertTrue(patientsWithChronicDiseaseAnemia.contains(pat_chronicDiseaseMALE));


        } finally {
            instance.close();
        }
    }

    @Test
    public void testChronicDiseaseAnemiaFerritineMALE_1Expected() {
        System.out.println("\n---test Chronic Disease Anemia Ferritine MALE 1 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithChronicDiseaseAnemia = patientUnit.getPatientsWithChronicDiseaseAnemia();
            assertEquals(1, patientUnit.getPatientsWithChronicDiseaseAnemia().size());
            assertTrue(patientsWithChronicDiseaseAnemia.contains(pat_chronicDiseaseMALE));


        } finally {
            instance.close();
        }
    }

    @Test
    public void testChronicDiseaseAnemiaFerritineFEMALE_2Expected() {
        System.out.println("\n---test Chronic Disease Anemia Ferritine FEMALE 1 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithChronicDiseaseAnemia = patientUnit.getPatientsWithChronicDiseaseAnemia();
            assertEquals(2, patientUnit.getPatientsWithChronicDiseaseAnemia().size());
            //EXPETE2 BEC MALE ALSO ADDED
            assertTrue(patientsWithChronicDiseaseAnemia.contains(pat_chronicDiseaseFEMALE));


        } finally {
            instance.close();
        }
    }

    @Test
    public void testChronicDiseaseAnemiaFerritineFEMALE_MALE_2Expected() {
        System.out.println("\n---test Chronic Disease Anemia Ferritine FEMALE or MALE 2 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithChronicDiseaseAnemia = patientUnit.getPatientsWithChronicDiseaseAnemia();
            assertEquals(2, patientUnit.getPatientsWithChronicDiseaseAnemia().size());

            assertTrue(patientsWithChronicDiseaseAnemia.contains(pat_chronicDiseaseFEMALE));
            assertTrue(patientsWithChronicDiseaseAnemia.contains(pat_chronicDiseaseMALE));



        } finally {
            instance.close();
        }
    }



//    @Test
//    public void testAuthorizedOperations() {
//        try {
//            LOG.info("Run query to find authorized operations. Rules are also fired");
//            instance.fire();
//            List<Operation> authorizedOperations = instance.executeQuery("FindAuthorizedOperations").toList("$operations");
//            assertEquals(1, authorizedOperations.size());
//            assertEquals(op3, authorizedOperations.get(0));
//        } finally {
//            instance.close();
//        }
//    }
//
//    @Test
//    public void testNonVerifiedOperationRule() {
//        try {
//            LOG.info("Run query to find non authorized operations due to non verified cards. Rules are also fired");
//            instance.fire();
//            Set<Operation> operaitionsNotVerified = operationUnit.getOperationsUnauthorizedNotVerified();
//            assertEquals(2, operaitionsNotVerified.size());
//            assertTrue(operaitionsNotVerified.contains(op6));
//            assertTrue(operaitionsNotVerified.contains(op7));
//
//        } finally {
//            instance.close();
//        }
//    }

}