package diagnosisTest;

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

public class PatientUnitAllSympTestWithSetUp {

    static final Logger LOG = LoggerFactory.getLogger(PatientUnitTestWithSetUp.class);

    PatientUnit patientUnit;
    RuleUnitInstance<PatientUnit> instance;
    Patient pat_AllAnemicSynd;
    Patient pat_AllPosthemorrhagic;

    @Before
    public void setUp() {

        LOG.info("Creating RuleUnit");
        patientUnit = new PatientUnit();

        instance = RuleUnitProvider.get().createRuleUnitInstance(patientUnit);
        LOG.info("Insert data");

        //ANEMIC SYNDROME
        pat_AllAnemicSynd = new Patient("All anemic syndrome symptoms PATIENT", 50 , Gender.MALE);
        Symptom hb1 = new Symptom(7.5F, "Hb", pat_AllAnemicSynd);
        pat_AllAnemicSynd.addSymptom(hb1);
        Symptom epo = new Symptom(18.7F, "EPO", pat_AllAnemicSynd);
        pat_AllAnemicSynd.addSymptom(epo);
        Symptom sp = new Symptom(1.0F, "Skin paleness", pat_AllAnemicSynd);
        pat_AllAnemicSynd.addSymptom(sp);
        Symptom tac = new Symptom(1.0F, "Tachycardia", pat_AllAnemicSynd);
        pat_AllAnemicSynd.addSymptom(tac);
        Symptom tin = new Symptom(1.0F, "Tinnitus", pat_AllAnemicSynd);
        pat_AllAnemicSynd.addSymptom(tin);
        Symptom cp = new Symptom(1.0F, "Chest pain", pat_AllAnemicSynd);
        pat_AllAnemicSynd.addSymptom(cp);
        Symptom inf = new Symptom(1.0F, "Infarction", pat_AllAnemicSynd);
        pat_AllAnemicSynd.addSymptom(inf);
        Symptom diz = new Symptom(1.0F, "Dizziness", pat_AllAnemicSynd);
        pat_AllAnemicSynd.addSymptom(diz);
        Symptom bv = new Symptom(1.0F, "Blurred vision", pat_AllAnemicSynd);
        pat_AllAnemicSynd.addSymptom(bv);
        Symptom head = new Symptom(1.0F, "Headache", pat_AllAnemicSynd);
        pat_AllAnemicSynd.addSymptom(head);
        Symptom fat = new Symptom(1.0F, "Fatigue", pat_AllAnemicSynd);
        pat_AllAnemicSynd.addSymptom(fat);
        Symptom mp = new Symptom(1.0F, "Muscle pain", pat_AllAnemicSynd);
        pat_AllAnemicSynd.addSymptom(mp);
        Symptom dys = new Symptom(1.0F, "Dyspnea", pat_AllAnemicSynd);
        pat_AllAnemicSynd.addSymptom(dys);
        Symptom nea = new Symptom(1.0F, "Tachypnea", pat_AllAnemicSynd);
        pat_AllAnemicSynd.addSymptom(nea);
        Symptom cold = new Symptom(1.0F, "Cold hands or feet", pat_AllAnemicSynd);
        pat_AllAnemicSynd.addSymptom(cold);


        //POSHEMORRHAGIC ANEMIA
        pat_AllPosthemorrhagic = new Patient("All poshemorrhagic anemia symptoms PATIENT", 50 , Gender.MALE);
        Symptom ret = new Symptom(2.8F, "Reticulocytes", pat_AllPosthemorrhagic);
        pat_AllPosthemorrhagic.addSymptom(ret);
        Symptom rbc1 = new Symptom(4.4F, "RBC", pat_AllPosthemorrhagic);
        pat_AllPosthemorrhagic.addSymptom(rbc1);
        Symptom vcm = new Symptom(95, "VCM", pat_AllPosthemorrhagic);
        pat_AllPosthemorrhagic.addSymptom(vcm);
        Symptom mchc = new Symptom(295, "MCHC", pat_AllPosthemorrhagic);
        pat_AllPosthemorrhagic.addSymptom(mchc);
        Symptom fe = new Symptom(58, "Fe", pat_AllPosthemorrhagic);
        pat_AllPosthemorrhagic.addSymptom(fe);
        Symptom sp1 = new Symptom(1.0F, "Skin paleness", pat_AllPosthemorrhagic);
        pat_AllPosthemorrhagic.addSymptom(sp1);
        Symptom tac1 = new Symptom(1.0F, "Tachycardia", pat_AllPosthemorrhagic);
        pat_AllPosthemorrhagic.addSymptom(tac1);
        Symptom dizz1 = new Symptom(1.0F, "Dizziness", pat_AllPosthemorrhagic);
        pat_AllPosthemorrhagic.addSymptom(dizz1);
        Symptom fat1 = new Symptom(1.0F, "Fatigue", pat_AllPosthemorrhagic);
        pat_AllPosthemorrhagic.addSymptom(fat1);
        Symptom dys1 = new Symptom(1.0F, "Dyspnea", pat_AllPosthemorrhagic);
        pat_AllPosthemorrhagic.addSymptom(dys1);
        Symptom nea1 = new Symptom(1.0F, "Tachypnea", pat_AllPosthemorrhagic);
        pat_AllPosthemorrhagic.addSymptom(nea1);
        Symptom eh = new Symptom(1.0F, "Extern hemorrhage", pat_AllPosthemorrhagic);
        pat_AllPosthemorrhagic.addSymptom(eh);

        patientUnit.getPatients().add(pat_AllAnemicSynd);
        patientUnit.getPatients().add(pat_AllPosthemorrhagic);



    }

    @Test
    public void testAnemicSyndrome() {
        System.out.println("\n--- test Anemic Syndrome ALL SYMPTOMS");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithAnemicSyndrome = patientUnit.getPatientsWithAnemicSyndrome();
            assertEquals(1, patientsWithAnemicSyndrome.size());
            assertTrue(patientsWithAnemicSyndrome.contains(pat_AllAnemicSynd));
            //System.out.println(patientUnit.getTestString());
        } finally {
            instance.close();
        }
    }

    @Test
    public void testPosthemorrhagicAnemia() {
        System.out.println("\n--- test Posthemorrhagic Anemia ALL SYMPTOMS");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithPosthemorrhagicAnemia = patientUnit.getPatientsWithPosthemorrhagicAnemia();
            assertEquals(1, patientsWithPosthemorrhagicAnemia.size());
            assertTrue(patientsWithPosthemorrhagicAnemia.contains(pat_AllPosthemorrhagic));

        } finally {
            instance.close();
        }
    }



    }
