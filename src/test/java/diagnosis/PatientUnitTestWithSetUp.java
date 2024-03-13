package diagnosis;


import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PatientUnitTestWithSetUp {

    static final Logger LOG = LoggerFactory.getLogger(PatientUnitTestWithSetUp.class);

    PatientUnit patientUnit;
    RuleUnitInstance<PatientUnit> instance;
    Patient p1;
    Patient p2;
    Patient p3;
    Patient p4;
    Patient p5;
    Patient p6;
    Patient p7;

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

        //No tiene nada
        p1 = new Patient(1, "Paco", 58, Gender.MALE, LocalDate.of(1987,10,12));
        Symptom s1 = new Symptom(1, 9, "Hb", p1);
        Symptom s4 = new Symptom(1, 4.1F, "RBC", p1);
        p1.addSymptom(s1);
        p1.addSymptom(s4);

        //Anemic Syndrome
        p2 = new Patient(2, "Marta", 18, Gender.FEMALE, LocalDate.of(1987,10,12));
        Symptom s2 = new Symptom(2, 7.5F, "Hb", p2);
        p2.addSymptom(s2);

        //no tiene nada
        p3 = new Patient(3, "Lucia", 58, Gender.FEMALE, LocalDate.of(1987,10,12));
        Symptom s3 = new Symptom(3, 20, "EPO", p3);
        p3.addSymptom(s3);


        patientUnit.getPatients().add(p1);
        patientUnit.getPatients().add(p2);
        patientUnit.getPatients().add(p3);
//        patientUnit.getPatients().add(p4);
//        patientUnit.getPatients().add(p5);
//        patientUnit.getPatients().add(p6);
//        patientUnit.getPatients().add(p7);

        // instance.close() should not be instantiated because it will be used in each test!
    }



    @Test
    public void testAnemicSyndromeHb1Expected() {
        System.out.println("testAnemicSyndromeHb1Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithAnemicSyndrome = patientUnit.getPatientsWithAnemicSyndrome();
            assertEquals(1, patientsWithAnemicSyndrome.size());
            assertTrue(patientsWithAnemicSyndrome.contains(p2));
            //System.out.println(patientUnit.getTestString());
        } finally {
            instance.close();
        }
    }
    @Test
    public void testPosthemorragicAnemiaMHCH1Expected() {
        System.out.println("testPosthemorragicAnemiaMHCH1Expected");
        try {
            LOG.info("Fire rules");
            instance.fire();

            Set<Patient> patientsWithPosthemorragicAnemia = patientUnit.getPatientsWithPosthemorragicAnemia();
            assertEquals(1, patientUnit.getPatientsWithPosthemorragicAnemia().size());
            assertTrue(patientsWithPosthemorragicAnemia.contains(p1));
            //System.out.println(patientUnit.getTestString());
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