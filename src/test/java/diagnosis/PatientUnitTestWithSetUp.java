package diagnosis;


import diagnosis.Gender;
import diagnosis.Patient;
import diagnosis.PatientUnit;
import diagnosis.Symptom;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PatientUnitTestWithSetUp {

    static final Logger LOG = LoggerFactory.getLogger(PatientUnitTestWithSetUp.class);

    PatientUnit patientUnit;
    RuleUnitInstance<PatientUnit> instance;
    Patient pat_posthemorrhagic;
    Patient pat_syndrome;
    Patient pat_hemolytic1;
    Patient pat_hemolytic2;
    Patient pat_spherocythosis;
    Patient pat_thalassemia;
    Patient pat_ironDef;
    Patient pat_megaloblastic;
    Patient pat_megaloblasticB12;
    Patient pat_megaloblasticFolicAc;
    Patient pat_aplastic;
    Patient pat_chronicDiseaseMALE;
    Patient pat_chronicDiseaseFEMALE;
    Patient pat_polycythemia1;
    Patient pat_polycythemia2;
    Patient pat_polycythemia3;
    Patient pat_polycythemia4;
    Patient pat_polycythemia5;



/**
     * This method will be run before EACH single test. If we want to have common code for all tests we can use @BeforeClass
     * In this case, the creations of the facts (operations) and the insertion in each instance will be common to each test, but they should be
     * run before each test, otherwise the instance will have already trigger/fire its rules so they will not be run apart from the first test if
     * the facts do not change (refractoriness).*/


    @Before
    public void setUp() {

        LOG.info("Creating RuleUnit");
        patientUnit = new PatientUnit();

        instance = RuleUnitProvider.get().createRuleUnitInstance(patientUnit);
        LOG.info("Insert data");

        //Anemic Syndrome
        pat_syndrome = new Patient("Marta", 18, Gender.FEMALE);
        Symptom hb1 = new Symptom(7.5F, "Hb", pat_syndrome);
        Symptom tin = new Symptom(1.0F, "Tinnitus", pat_syndrome);
        Symptom chp = new Symptom(1.0F, "Chest pain", pat_syndrome);
        Symptom inf = new Symptom(1.0F, "Infarction", pat_syndrome);
        Symptom mp = new Symptom(1.0F, "Muscle pain", pat_syndrome);
        pat_syndrome.addSymptom(hb1);
        pat_syndrome.addSymptom(tin);
        pat_syndrome.addSymptom(chp);
        pat_syndrome.addSymptom(inf);
        pat_syndrome.addSymptom(mp);

        //Posthemorragic anemia
        pat_posthemorrhagic = new Patient("Paco", 58, Gender.MALE);
        Symptom hb = new Symptom(9, "Hb", pat_posthemorrhagic);
        Symptom rbcM = new Symptom(4.1F, "RBC", pat_posthemorrhagic);
        Symptom mhch = new Symptom(307F, "MHCH", pat_posthemorrhagic);
        Symptom fe1 = new Symptom(55, "Fe", pat_posthemorrhagic);
        Symptom eh = new Symptom(1.0F, "Extern hemorrhage", pat_posthemorrhagic);
        Symptom ih = new Symptom(1.0F, "Intern hemorrhage", pat_posthemorrhagic);
        pat_posthemorrhagic.addSymptom(hb);
        pat_posthemorrhagic.addSymptom(rbcM);
        pat_posthemorrhagic.addSymptom(mhch);
        pat_posthemorrhagic.addSymptom(fe1);
        pat_posthemorrhagic.addSymptom(eh);
        pat_posthemorrhagic.addSymptom(ih);


        //Hemolytic anemia
        pat_hemolytic1 = new Patient("Lucia", 58, Gender.FEMALE);
        Symptom mch = new Symptom(40, "MCH", pat_hemolytic1);
        Symptom fe2 = new Symptom(200, "MCH", pat_hemolytic1);
        Symptom hapto = new Symptom(35, "MCH", pat_hemolytic1);
        Symptom hb_greaterFEMALE = new Symptom(11.7F, "Hb", pat_hemolytic1);
        pat_hemolytic1.addSymptom(mch);
        pat_hemolytic1.addSymptom(fe2);
        pat_hemolytic1.addSymptom(hapto);
        pat_hemolytic1.addSymptom(hb_greaterFEMALE);

        pat_hemolytic2 = new Patient("Lucas", 35, Gender.MALE);
        Symptom hb_greaterMALE = new Symptom(11.7F, "Hb", pat_hemolytic2);
        Symptom feUr = new Symptom(1.0F, "Fe in urine", pat_hemolytic2);
        Symptom hbUr = new Symptom(1.0F, "Hb in urine", pat_hemolytic2);
        pat_hemolytic2.addSymptom(hb_greaterMALE);
        pat_hemolytic2.addSymptom(feUr);
        pat_hemolytic2.addSymptom(hbUr);


        //Inherited Spherocythosis
        pat_spherocythosis = new Patient("Mar", 42, Gender.FEMALE);
        Symptom bi = new Symptom(1.0F, "Bacterial infection", pat_spherocythosis);
        Symptom ost = new Symptom(1.0F, "Osteoporosis", pat_spherocythosis);
        Symptom maxHyp = new Symptom(1.0F, "Maxilar bone hyperplasia", pat_spherocythosis);
        Symptom boneDef = new Symptom(1.0F, "Bone deformations", pat_spherocythosis);
        pat_spherocythosis.addSymptom(bi);
        pat_spherocythosis.addSymptom(ost);
        pat_spherocythosis.addSymptom(maxHyp);
        pat_spherocythosis.addSymptom(boneDef);


        //Thalassemia
        pat_thalassemia = new Patient("Francisco", 56, Gender.MALE);
        Symptom nau = new Symptom(1.0F, "Nausea or poor appetite", pat_thalassemia);
        Symptom bn = new Symptom(1.0F, "Brittle nails", pat_thalassemia);
        Symptom ti = new Symptom(1.0F, "Tongue inflamation", pat_thalassemia);
        pat_thalassemia.addSymptom(nau);
        pat_thalassemia.addSymptom(bn);
        pat_thalassemia.addSymptom(ti);


        //Iron deficiency anemia
        pat_ironDef = new Patient("Isidoro", 81, Gender.MALE);
        Symptom mch2 = new Symptom(26, "MCH", pat_ironDef);
        Symptom ferr = new Symptom(10, "Ferritin", pat_ironDef);
        Symptom dia = new Symptom(1.0F, "Diahrrea", pat_ironDef);
        Symptom par = new Symptom(1.0F, "Paresthesia", pat_ironDef);
        pat_ironDef.addSymptom(mch2);
        pat_ironDef.addSymptom(ferr);
        pat_ironDef.addSymptom(par);

        //Megaloblastic anemia
        pat_megaloblastic = new Patient("Almudena", 29, Gender.FEMALE);
        Symptom b12 = new Symptom(177, "B12", pat_megaloblastic);
        Symptom folicAc = new Symptom(2.55F, "Folic Acid", pat_megaloblastic);
        Symptom pet = new Symptom(1.0F, "Petechiae", pat_megaloblastic);
        pat_megaloblastic.addSymptom(b12);
        pat_megaloblastic.addSymptom(folicAc);
        pat_megaloblastic.addSymptom(pet);
        //Megaloblastic anemia b12
        pat_megaloblasticB12 = new Patient("Lola", 55, Gender.FEMALE);
        Symptom b12vit = new Symptom(185, "B12", pat_megaloblasticB12);
        pat_megaloblasticB12.addSymptom(b12vit);
        //Megaloblastic anemia Folic Acid
        pat_megaloblasticFolicAc = new Patient("Pepe", 43, Gender.MALE);
        Symptom fa = new Symptom(2, "B12", pat_megaloblasticFolicAc);
        pat_megaloblasticFolicAc.addSymptom(fa);


        //Aplastic Anemia
        pat_aplastic = new Patient("Natalia", 27,  Gender.FEMALE);
        Symptom plaq = new Symptom(7, "Plaquets", pat_aplastic);
        Symptom leuk = new Symptom(7, "Leukocytes", pat_aplastic);
        pat_aplastic.addSymptom(plaq);
        pat_aplastic.addSymptom(leuk);

        pat_chronicDiseaseMALE = new Patient("Gustavo", 44,  Gender.MALE);
        Symptom epo = new Symptom(2.3F, "EPO", pat_chronicDiseaseMALE);
        Symptom ferr1 = new Symptom(310, "Ferritine", pat_chronicDiseaseMALE);
        pat_chronicDiseaseMALE.addSymptom(epo);
        pat_chronicDiseaseMALE.addSymptom(ferr1);

        pat_chronicDiseaseFEMALE = new Patient("Gadea", 44,  Gender.FEMALE);
        Symptom ferr2 = new Symptom(210, "Ferritine", pat_chronicDiseaseFEMALE);
        pat_chronicDiseaseFEMALE.addSymptom(ferr2);


        //Polycythemia
        pat_polycythemia1 = new Patient("Manolo", 76, Gender.MALE);
        Symptom hb2 = new Symptom(17.8F, "Hb", pat_polycythemia1);
        Symptom pvm1 = new Symptom(52, "PVM", pat_polycythemia1);
        pat_polycythemia1.addSymptom(hb2);
        pat_polycythemia1.addSymptom(pvm1);

        pat_polycythemia2 = new Patient("Manuel", 60, Gender.MALE);
        Symptom hb3 = new Symptom(16.3F, "Hb", pat_polycythemia2);
        Symptom pvm2 = new Symptom(55, "PVM", pat_polycythemia2);
        pat_polycythemia2.addSymptom(hb3);
        pat_polycythemia2.addSymptom(pvm2);

        pat_polycythemia3 = new Patient("Loli", 56, Gender.FEMALE);
        Symptom pvm3 = new Symptom(50, "PVM", pat_polycythemia3);
        Symptom cya = new Symptom(1.0F, "Cyanosis", pat_polycythemia3);
        pat_polycythemia3.addSymptom(pvm3);
        pat_polycythemia3.addSymptom(cya);

        pat_polycythemia4 = new Patient("Loli", 56, Gender.FEMALE);
        Symptom glucocor = new Symptom(27, "Glucocorticoids", pat_polycythemia4);
        Symptom thr= new Symptom(1.0F, "Thrombosis", pat_polycythemia4);
        pat_polycythemia4.addSymptom(glucocor);
        pat_polycythemia4.addSymptom(thr);

        pat_polycythemia5 = new Patient("Dolores", 56, Gender.FEMALE);
        Symptom RBC = new Symptom(5.9F, "RBC", pat_polycythemia5);
        Symptom vom = new Symptom(1.0F, "Vomit", pat_polycythemia5);
        pat_polycythemia5.addSymptom(RBC);
        pat_polycythemia5.addSymptom(vom);

//---------------------------------------------------------------------------------------

        patientUnit.getPatients().add(pat_posthemorrhagic);
        patientUnit.getPatients().add(pat_syndrome);
        patientUnit.getPatients().add(pat_hemolytic1);
        patientUnit.getPatients().add(pat_hemolytic2);
        patientUnit.getPatients().add(pat_spherocythosis);
        patientUnit.getPatients().add(pat_thalassemia);
        patientUnit.getPatients().add(pat_ironDef);
        patientUnit.getPatients().add(pat_megaloblastic);
        patientUnit.getPatients().add(pat_megaloblasticB12);
        patientUnit.getPatients().add(pat_megaloblasticFolicAc);
        patientUnit.getPatients().add(pat_aplastic);
        patientUnit.getPatients().add(pat_chronicDiseaseMALE);
        patientUnit.getPatients().add(pat_chronicDiseaseFEMALE);
        patientUnit.getPatients().add(pat_polycythemia1);
        patientUnit.getPatients().add(pat_polycythemia2);
        patientUnit.getPatients().add(pat_polycythemia3);
        patientUnit.getPatients().add(pat_polycythemia4);
        patientUnit.getPatients().add(pat_polycythemia5);


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
        System.out.println("\n--- test Anemic Syndrome Hb 1 Expected");
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
    public void testAnemicSyndromeTinnitus_1Expected() {
        System.out.println("\n--- test Anemic Syndrome Tinnitus 1Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithAnemicSyndrome = patientUnit.getPatientsWithAnemicSyndrome();
            assertEquals(1, patientsWithAnemicSyndrome.size());
            assertTrue(patientsWithAnemicSyndrome.contains(pat_syndrome));

        } finally {
            instance.close();
        }
    }

    @Test
    public void testAnemicSyndromeChestPain_1Expected() {
        System.out.println("\n--- test Anemic Syndrome Chest pain 1Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithAnemicSyndrome = patientUnit.getPatientsWithAnemicSyndrome();
            assertEquals(1, patientsWithAnemicSyndrome.size());
            assertTrue(patientsWithAnemicSyndrome.contains(pat_syndrome));

        } finally {
            instance.close();
        }
    }

    @Test
    public void testAnemicSyndromeInfarction_1Expected() {
        System.out.println("\n--- test Anemic Syndrome Chest pain 1Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithAnemicSyndrome = patientUnit.getPatientsWithAnemicSyndrome();
            assertEquals(1, patientsWithAnemicSyndrome.size());
            assertTrue(patientsWithAnemicSyndrome.contains(pat_syndrome));

        } finally {
            instance.close();
        }
    }

    @Test
    public void testAnemicSyndromeMusclePain_1Expected() {
        System.out.println("\n--- test Anemic Syndrome Muscle pain 1Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithAnemicSyndrome = patientUnit.getPatientsWithAnemicSyndrome();
            assertEquals(1, patientsWithAnemicSyndrome.size());
            assertTrue(patientsWithAnemicSyndrome.contains(pat_syndrome));

        } finally {
            instance.close();
        }
    }
    @Test
    public void testPosthemorragicAnemiaRBC_1Expected() {
        System.out.println("\n--- test Posthemorragic Anemia MHCH 1 Expected");
        try {
            LOG.info("Fire rules");
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
        System.out.println("\n--- test Posthemorragic Anemia MHCH 1Expected");
        try {
            LOG.info("Fire rules");
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
        System.out.println("\n--- test Posthemorragic Anemia Fe 1Expected");
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
    public void testPosthemorrhagicAnemiaExtHemorrage_1Expected() {
        System.out.println("\n--- test Posthemorragic Anemia Fe 1 Expected");
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
    public void testPosthemorrhagicAnemiaIntHemorrage_1Expected() {
        System.out.println("\n---test Posthemorragic Anemia Fe 1 Expected");
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
    public void testHemolyticAnemiaHb_2Expected() {
        System.out.println("\n---test Hemolytic Anemia Hb 2 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithHemolyticAnemia = patientUnit.getPatientsWithHemolyticAnemia();
            assertEquals(2, patientUnit.getPatientsWithHemolyticAnemia().size());
            assertTrue(patientsWithHemolyticAnemia.contains(pat_hemolytic1));
            assertTrue(patientsWithHemolyticAnemia.contains(pat_hemolytic2));

        } finally {
            instance.close();
        }
    }

    @Test
    public void testHemolyticAnemiaMCH_2Expected() {
        System.out.println("\n---test Hemolytic Anemia MCH 1Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithHemolyticAnemia = patientUnit.getPatientsWithHemolyticAnemia();
            assertEquals(2, patientUnit.getPatientsWithHemolyticAnemia().size());
            assertTrue(patientsWithHemolyticAnemia.contains(pat_hemolytic1));
            System.out.println(patientUnit.getTestString());

        } finally {
            instance.close();
        }
    }

    @Test
    public void testHemolyticAnemiaFeUrine_2Expected() {
        System.out.println("\n---test Hemolytic Anemia Fe in urine 1Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithHemolyticAnemia = patientUnit.getPatientsWithHemolyticAnemia();
            assertEquals(2, patientUnit.getPatientsWithHemolyticAnemia().size());
            assertTrue(patientsWithHemolyticAnemia.contains(pat_hemolytic2));
            System.out.println(patientUnit.getTestString());

        } finally {
            instance.close();
        }
    }

    @Test
    public void testHemolyticAnemiaHbUrine_2Expected() {
        System.out.println("\n---test Hemolytic Anemia Hb in urine 1Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithHemolyticAnemia = patientUnit.getPatientsWithHemolyticAnemia();
            assertEquals(2, patientUnit.getPatientsWithHemolyticAnemia().size());
            assertTrue(patientsWithHemolyticAnemia.contains(pat_hemolytic2));
            System.out.println(patientUnit.getTestString());

        } finally {
            instance.close();
        }
    }

    @Test
    public void testHemolyticAnemiaFe2_Expected() {

        System.out.println("\n---test Hemolytic Anemia Fe 1Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithHemolyticAnemia = patientUnit.getPatientsWithHemolyticAnemia();
            assertEquals(2, patientUnit.getPatientsWithHemolyticAnemia().size());
            assertTrue(patientsWithHemolyticAnemia.contains(pat_hemolytic1));
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
            assertTrue(patientsWithHemolyticAnemia.contains(pat_hemolytic1));

        } finally {
            instance.close();
        }
    }

    @Test
    public void testInheritedSpherocythosisBactInf_1Expected() {
        System.out.println("\n---test Inherited Spherocythosis BactInf 1Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithInheritedSpherocythosis = patientUnit.getPatientsWithInheritedSpherocythosis();
            assertEquals(1, patientUnit.getPatientsWithInheritedSpherocythosis().size());
            assertTrue(patientsWithInheritedSpherocythosis.contains(pat_spherocythosis));

        } finally {
            instance.close();
        }
    }

    @Test
    public void testInheritedSpherocythosisOsteoporosis_1Expected() {
        System.out.println("\n---test Inherited Spherocythosis Osteoporosis 1 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithInheritedSpherocythosis = patientUnit.getPatientsWithInheritedSpherocythosis();
            assertEquals(1, patientUnit.getPatientsWithInheritedSpherocythosis().size());
            assertTrue(patientsWithInheritedSpherocythosis.contains(pat_spherocythosis));

        } finally {
            instance.close();
        }
    }

    @Test
    public void testInheritedSpherocythosisBoneDeformations_1Expected() {
        System.out.println("\n---test Inherited Spherocythosis Bone Deformations 1 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithInheritedSpherocythosis = patientUnit.getPatientsWithInheritedSpherocythosis();
            assertEquals(1, patientUnit.getPatientsWithInheritedSpherocythosis().size());
            assertTrue(patientsWithInheritedSpherocythosis.contains(pat_spherocythosis));

        } finally {
            instance.close();
        }
    }
    @Test
    public void testInheritedSpherocythosisMaxilarBoneHyperplasia_1Expected() {
        System.out.println("\n---test Inherited Spherocythosis Osteoporosis 1 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithInheritedSpherocythosis = patientUnit.getPatientsWithInheritedSpherocythosis();
            assertEquals(1, patientUnit.getPatientsWithInheritedSpherocythosis().size());
            assertTrue(patientsWithInheritedSpherocythosis.contains(pat_spherocythosis));

        } finally {
            instance.close();
        }
    }

    @Test
    public void testThalassemiaNausea_1Expected() {
        System.out.println("\n---test Thalassemia Nausea 1 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithThalassemia = patientUnit.getPatientsWithThalassemia();
            assertEquals(1, patientUnit.getPatientsWithThalassemia().size());
            assertTrue(patientsWithThalassemia.contains(pat_thalassemia));

        } finally {
            instance.close();
        }
    }

    @Test
    public void testThalassemiaBrittleNails_1Expected() {
        System.out.println("\n---test Thalassemia Brittle nails 1 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithThalassemia = patientUnit.getPatientsWithThalassemia();
            assertEquals(1, patientUnit.getPatientsWithInheritedSpherocythosis().size());
            assertTrue(patientsWithThalassemia.contains(pat_thalassemia));

        } finally {
            instance.close();
        }
    }

    @Test
    public void testThalassemiaTongueInflamation_1Expected() {
        System.out.println("\n---test Thalassemia Tongue inflamation 1 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithThalassemia = patientUnit.getPatientsWithThalassemia();
            assertEquals(1, patientUnit.getPatientsWithInheritedSpherocythosis().size());
            assertTrue(patientsWithThalassemia.contains(pat_thalassemia));

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
    public void testIronDefAnemiaFerritin_1Expected() {
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
    public void testIronDefAnemiaDiahrrea_1Expected() {
        System.out.println("\n---test Iron deficiency Anemia Diahrrea 1 Expected");
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
    public void testIronDefAnemiaParesthesia_1Expected() {
        System.out.println("\n---test Iron deficiency Anemia Paresthesia 1 Expected");
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
        System.out.println("\n---test Megaloblastic Anemia B12 OR Folic Acid 3 Expected");
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
    public void testMegaloblasticAnemiaPetechiae_OR_3Expected() {
        System.out.println("\n---test Megaloblastic Anemia Petechiae 3 Expected");
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

    @Test
    public void testPolycythemiaHb_1Expected() {
        System.out.println("\n---test Polycythemia Hb 1 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithPolycythemia = patientUnit.getPatientsWithPolycythemia();
            assertEquals(5, patientUnit.getPatientsWithPolycythemia().size());
            assertTrue(patientsWithPolycythemia.contains(pat_polycythemia1));


        } finally {
            instance.close();
        }
    }

    @Test
    public void testPolycythemiaPVM_3Expected() {
        System.out.println("\n---test Polycythemia PVM 3 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithPolycythemia = patientUnit.getPatientsWithPolycythemia();
            assertEquals(3, patientUnit.getPatientsWithPolycythemia().size());
            //Hb pat1
            assertTrue(patientsWithPolycythemia.contains(pat_polycythemia2));
            assertTrue(patientsWithPolycythemia.contains(pat_polycythemia3));


        } finally {
            instance.close();
        }
    }

    @Test
    public void testPolycythemiaGlucocorticoids_4Expected() {
        System.out.println("\n---test Polycythemia glucocorticoids 4 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithPolycythemia = patientUnit.getPatientsWithPolycythemia();
            assertEquals(4, patientUnit.getPatientsWithPolycythemia().size());
            assertTrue(patientsWithPolycythemia.contains(pat_polycythemia4));


        } finally {
            instance.close();
        }
    }

    @Test
    public void testPolycythemiaCyanosis_5Expected() {
        System.out.println("\n---test Polycythemia cyanosis 5 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithPolycythemia = patientUnit.getPatientsWithPolycythemia();
            assertEquals(5, patientUnit.getPatientsWithPolycythemia().size());
            assertTrue(patientsWithPolycythemia.contains(pat_polycythemia3));


        } finally {
            instance.close();
        }
    }

    @Test
    public void testPolycythemiaRBC_5Expected() {
        System.out.println("\n---test Polycythemia RBC 5 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithPolycythemia = patientUnit.getPatientsWithPolycythemia();
            assertEquals(5, patientUnit.getPatientsWithPolycythemia().size());
            assertTrue(patientsWithPolycythemia.contains(pat_polycythemia5));


        } finally {
            instance.close();
        }
    }

    @Test
    public void testPolycythemiaVomit_5Expected() {
        System.out.println("\n---test Polycythemia RBC 5 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithPolycythemia = patientUnit.getPatientsWithPolycythemia();
            assertEquals(5, patientUnit.getPatientsWithPolycythemia().size());
            assertTrue(patientsWithPolycythemia.contains(pat_polycythemia5));


        } finally {
            instance.close();
        }
    }

    @Test
    public void testPolycythemiaThrombosis_5Expected() {
        System.out.println("\n---test Polycythemia Thrombosis 5 Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithPolycythemia = patientUnit.getPatientsWithPolycythemia();
            assertEquals(5, patientUnit.getPatientsWithPolycythemia().size());
            assertTrue(patientsWithPolycythemia.contains(pat_polycythemia4));


        } finally {
            instance.close();
        }
    }


}
