package diagnosis;


import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PatientUnitDiagnosisTestWithSetUp {

    static final Logger LOG = LoggerFactory.getLogger(PatientUnitDiagnosisTestWithSetUp.class);

    PatientUnit patientUnit;
    RuleUnitInstance<PatientUnit> instance;
    Patient p1;
    Patient p2;
    Patient p3;


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

        //Patient 1
        /*Anemic syndrome
        * Posthemorrhagic anemia
        * Hemolytic anemia
        * Inherited sperocytosis
        * Thalassemia
        * Megaloblastic Anemia
        * Aplasic anemia
        * Polycythemia*/

        p1 = new Patient("Juan", 18, Gender.MALE);
        Symptom hb1 = new Symptom(7.5F, "Hb");
        Symptom tach1 = new Symptom(1.0F, "Tachycardia");
        Symptom mhch1 = new Symptom(300F, "MHCH");
        Symptom fat1 = new Symptom(1.0F, "Fatigue");
        Symptom splen1 = new Symptom(1.0F, "Splenomegaly");
        Symptom bili1 = new Symptom(3F, "Bilirrubine");
        Symptom jaun1 = new Symptom(1.0F, "Jaundice");
        Symptom bonedef1 = new Symptom(1.0F, "Bone deformations");
        Symptom tongue1 = new Symptom(1.0F, "Tongue inflamation");
        Symptom ferr1 = new Symptom(10F, "Ferritin");
        Symptom skin1 = new Symptom(1.0F, "Skin paleness");
        Symptom internh1 = new Symptom(1.0F, "Intern hemorrhage");
        Symptom IBV1 = new Symptom(1.0F, "Increased blood volume");
        Symptom gluc1 = new Symptom(30F, "Glucocorticoids");

        p1.addSymptom(hb1);
        p1.addSymptom(tach1);
        p1.addSymptom(mhch1);
        p1.addSymptom(fat1);
        p1.addSymptom(splen1);
        p1.addSymptom(bili1);
        p1.addSymptom(jaun1);
        p1.addSymptom(bonedef1);
        p1.addSymptom(tongue1);
        p1.addSymptom(ferr1);
        p1.addSymptom(skin1);
        p1.addSymptom(internh1);
        p1.addSymptom(IBV1);
        p1.addSymptom(gluc1);



        //Patient 2
        /*Hemolytic anemia
        * Inherited spherocythosis
        * Megaloblastic anemia
        * Aplasic anemia
        * Chronic anemia*/

        p2 = new Patient("Lucía", 58, Gender.FEMALE);
        Symptom fe2 = new Symptom(140F, "Fe");
        Symptom hapto2 = new Symptom(46F, "Haptoglobin");
        /*Symptom bili1 = new Symptom(3F, "Bilirrubine");
        Symptom jaun1 = new Symptom(1.0F, "Jaundice");*/
        Symptom hb2 = new Symptom(8.1F, "Hb");
        //Symptom tach1 = new Symptom(1.0F, "Tachycardia");
        //Symptom fat1 = new Symptom(1.0F, "Fatigue");
        Symptom mhch2 = new Symptom(378F, "MHCH");
        Symptom b12_2 = new Symptom(140F, "B12");
        Symptom reticu2 = new Symptom(2.7F, "Reticulocytes");
        Symptom mch2 = new Symptom(26F, "MCH");
        Symptom plaquets2 = new Symptom(7.2F, "Plaquets");
        Symptom Leukoc2 = new Symptom(4.3F, "Leukocytes");
        Symptom rbc2 = new Symptom(3.9F, "RBC");
        Symptom epo2 = new Symptom(2.3F, "EPO");
        //Symptom skin1 = new Symptom(1.0F, "Skin paleness");

        p2.addSymptom(fe2);
        p2.addSymptom(hapto2);
        p2.addSymptom(bili1);
        p2.addSymptom(jaun1);
        p2.addSymptom(hb2);
        p2.addSymptom(tach1);
        p2.addSymptom(fat1);
        p2.addSymptom(mhch2);
        p2.addSymptom(b12_2);
        p2.addSymptom(reticu2);
        p2.addSymptom(mch2);
        p2.addSymptom(plaquets2);
        p2.addSymptom(Leukoc2);
        p2.addSymptom(rbc2);
        p2.addSymptom(epo2);
        p2.addSymptom(skin1);


        //Patient 3
        /*Iron deficiency anemia
        * Chronic anemia
        * Polycytemia*/
        p3 = new Patient("Pepe", 58, Gender.MALE);
        Symptom bili3 = new Symptom(0.2F, "Bilirrubine");
        //Symptom jaun1 = new Symptom(1.0F, "Jaundice");
        Symptom folic3 = new Symptom(3F, "Folic acid");
        //Symptom fat1 = new Symptom(1.0F, "Fatigue");
        Symptom reticu3 = new Symptom(2.1F, "Reticulocytes");
        //Symptom skin1 = new Symptom(1.0F, "Skin paleness");
        //Symptom plaquets2 = new Symptom(7.2F, "Plaquets");
        //Symptom Leukoc2 = new Symptom(4.3F, "Leukocytes");
        Symptom rbc3 = new Symptom(6.2F, "RBC");
        Symptom ferritin3 = new Symptom(350, "Ferritin");
        Symptom hb3 = new Symptom(18F, "Hb");
        Symptom pvm3 = new Symptom(60F, "PVM");

        p3.addSymptom(bili3);
        p3.addSymptom(jaun1);
        p3.addSymptom(folic3);
        p3.addSymptom(reticu3);
        p3.addSymptom(skin1);
        p3.addSymptom(plaquets2);
        p3.addSymptom(Leukoc2);
        p3.addSymptom(rbc3);
        p3.addSymptom(ferritin3);
        p3.addSymptom(hb3);
        p3.addSymptom(pvm3);



        patientUnit.getPatients().add(p1);
        patientUnit.getPatients().add(p2);
        patientUnit.getPatients().add(p3);
   }


    @Test
    public void testAnemicSyndrome_1Expected() {
        System.out.println("\n--- test Anemic Syndrome 1 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithAnemicSyndrome = patientUnit.getPatientsWithAnemicSyndrome();
            assertEquals(1, patientsWithAnemicSyndrome.size());
            assertTrue(patientsWithAnemicSyndrome.contains(p1));
        } finally {
            instance.close();
        }
    }


    @Test
    public void testPosthemorragicAnemia_1Expected() {
        System.out.println("\n--- test Posthemorragic Anemia 1 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithPosthemorragicAnemia = patientUnit.getPatientsWithPosthemorrhagicAnemia();
            assertEquals(1, patientUnit.getPatientsWithPosthemorrhagicAnemia().size());
            assertTrue(patientsWithPosthemorragicAnemia.contains(p1));
            //System.out.println(patientUnit.getTestString());
        } finally {
            instance.close();
        }
    }


    @Test
    public void testHemolyticAnemia_2Expected() {
        System.out.println("\n---test Hemolytic Anemia 2 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithHemolyticAnemia = patientUnit.getPatientsWithHemolyticAnemia();
            assertEquals(2, patientUnit.getPatientsWithHemolyticAnemia().size());
            assertTrue(patientsWithHemolyticAnemia.contains(p1));
            assertTrue(patientsWithHemolyticAnemia.contains(p2));

        } finally {
            instance.close();
        }
    }


    @Test
    public void testInheritedSpherocythosis_2Expected() {
        System.out.println("\n---test Inherited Spherocythosis 2 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithInheritedSpherocythosis = patientUnit.getPatientsWithInheritedSpherocythosis();
            assertEquals(2, patientUnit.getPatientsWithInheritedSpherocythosis().size());
            assertTrue(patientsWithInheritedSpherocythosis.contains(p1));
            assertTrue(patientsWithInheritedSpherocythosis.contains(p2));


        } finally {
            instance.close();
        }
    }


    @Test
    public void testThalassemia_1Expected() {
        System.out.println("\n---test Thalassemia 1 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithThalassemia = patientUnit.getPatientsWithThalassemia();
            assertEquals(1, patientUnit.getPatientsWithThalassemia().size());
            assertTrue(patientsWithThalassemia.contains(p1));

        } finally {
            instance.close();
        }
    }

    @Test
    public void testIronDefAnemia_3Expected() {
        System.out.println("\n---test Iron deficiency Anemia 3 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithIronDeficiencyAnemia = patientUnit.getPatientsWithIronDeficiencyAnemia();
            assertEquals(3, patientUnit.getPatientsWithIronDeficiencyAnemia().size());
            assertTrue(patientsWithIronDeficiencyAnemia.contains(p1));
            assertTrue(patientsWithIronDeficiencyAnemia.contains(p2));
            assertTrue(patientsWithIronDeficiencyAnemia.contains(p3));

        } finally {
            instance.close();
        }
    }


    @Test
    public void testMegaloblasticAnemia_2Expected() {
        System.out.println("\n---test Megaloblastic Anemia 2 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithMegaloblasticAnemia = patientUnit.getPatientsWithMegaloblasticAnemia();
            assertEquals(2, patientUnit.getPatientsWithMegaloblasticAnemia().size());
            assertTrue(patientsWithMegaloblasticAnemia.contains(p1));
            assertTrue(patientsWithMegaloblasticAnemia.contains(p2));

        } finally {
            instance.close();
        }
    }


    @Test
    public void testAplasicAnemia_2Expected() {
        System.out.println("\n---test Aplastic Anemia Plaquets 2 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithAplasticAnemia = patientUnit.getPatientsWithAplasticAnemia();
            assertEquals(2, patientUnit.getPatientsWithAplasticAnemia().size());
            assertTrue(patientsWithAplasticAnemia.contains(p1));
            assertTrue(patientsWithAplasticAnemia.contains(p2));


        } finally {
            instance.close();
        }
    }


    @Test
    public void testChronicDiseaseAnemia_2Expected() {
        System.out.println("\n---test Chronic Disease Anemia 2 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithChronicDiseaseAnemia = patientUnit.getPatientsWithChronicDiseaseAnemia();
            assertEquals(2, patientUnit.getPatientsWithChronicDiseaseAnemia().size());
            assertTrue(patientsWithChronicDiseaseAnemia.contains(p2));
            assertTrue(patientsWithChronicDiseaseAnemia.contains(p3));

        } finally {
            instance.close();
        }
    }


    @Test
    public void testPolycythemia_2Expected() {
        System.out.println("\n---test Polycythemia 2 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithPolycythemia = patientUnit.getPatientsWithPolycythemia();
            assertEquals(2, patientUnit.getPatientsWithPolycythemia().size());
            assertTrue(patientsWithPolycythemia.contains(p1));
            assertTrue(patientsWithPolycythemia.contains(p3));


        } finally {
            instance.close();
        }
    }



}
