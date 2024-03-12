package diagnosis;

import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PatientUnitTest {

//    static final Logger LOG = LoggerFactory.getLogger(OperationUnitTest.class);
//
//    @Test
//    public void testAnemicSyndromeHb() {
//        System.out.println("First test");
//        LOG.info("Creating RuleUnit");
//        PatientUnit patientUnit = new PatientUnit();
//
//        RuleUnitInstance<PatientUnit> instance = RuleUnitProvider.get().createRuleUnitInstance(patientUnit);
//        try {
//            LOG.info("Insert data");
//            Patient p1 = new Patient(1, "Paco", 23, Gender.MALE);
//            Symptom s1 = new Symptom(1, new Date(), p1);
//            p1.addSign(s1);
//            Quantitative qt1 = new Quantitative(1, "Hb", 17.3F);
//            s1.addSymptom(qt1);
//
//
////            System.out.println(p1);
////            System.out.println(p1.getSigns());
//
//            Patient p2 = new Patient(2, "Marta", 53, Gender.FEMALE);
//            Symptom s2 = new Symptom(2, new Date(), p2);
//            p2.addSign(s2);
//
//            Quantitative qt2 = new Quantitative(2, "Hb", 7F);
//            Quantitative qt3 = new Quantitative(3, "VCM", 16.5F);
//            s2.addSymptom(qt2);
//            s2.addSymptom(qt3);
//
//            System.out.println(p2.getSigns());
//
//            patientUnit.getPatients().add(p1);
//            patientUnit.getPatients().add(p2);
//
///*            Operation op1 = new Operation(1, true, false, true, Tries.EXPIRED, 100, 1000, 500);
//            Operation op2 = new Operation(2, true, false, true, Tries.NOT_EXPIRED, 100, 1000, 500);
//            Operation op3 = new Operation(3, true, false, true, Tries.NOT_EXPIRED, 1000, 100, 500);
//            Operation op4 = new Operation(4, true, false, true, Tries.NOT_EXPIRED, 1000, 10000, 500);
//            Operation op5 = new Operation(5, true, true, true, Tries.EXPIRED, 100, 1000, 500);
//            Operation op6 = new Operation(6, false, true, true, Tries.NOT_EXPIRED, 100, 1000, 500);
//            Operation op7 = new Operation(7, false, true, true, Tries.NOT_EXPIRED, 100, 1000, 500);
//
//            operationUnit.getOperations().add(op1);
//            operationUnit.getOperations().add(op2);
//            operationUnit.getOperations().add(op3);
//            operationUnit.getOperations().add(op4);
//            operationUnit.getOperations().add(op5);
//            operationUnit.getOperations().add(op6);
//            operationUnit.getOperations().add(op7);*/
//
//            LOG.info("Fire rules");
//            instance.fire();
//            Set<Patient> patientsWithAnemicSyndrome = patientUnit.getPatientsWithAnemicSyndrome();
//            assertEquals(1, patientsWithAnemicSyndrome.size());
//            assertTrue(patientsWithAnemicSyndrome.contains(p2));
//
//            //System.out.println(patientUnit.getTestString());
//
//
//        } finally {
//            instance.close();
//        }
//    }
//
//    @Test
//    public void testAnemicSyndromeTinnitius() {
//        LOG.info("Creating RuleUnit");
//        PatientUnit patientUnit = new PatientUnit();
//
//        RuleUnitInstance<PatientUnit> instance = RuleUnitProvider.get().createRuleUnitInstance(patientUnit);
//        try {
//            LOG.info("Insert data");
//            Patient p1 = new Patient(1, "Paco", 23, Gender.MALE);
//            Symptom s1 = new Symptom(1, new Date(), p1);
//            p1.addSign(s1);
//
//            Quantitative qt1 = new Quantitative(1, "Hb", 17.3F);
//            s1.addSymptom(qt1);
//            Qualitative ql1 = new Qualitative(1, "tinnitius", true);
//            s1.addSymptom(ql1);
//
//            patientUnit.getPatients().add(p1);
//
//            LOG.info("Fire rules");
//            instance.fire();
//            Set<Patient> patientsWithAnemicSyndrome = patientUnit.getPatientsWithAnemicSyndrome();
//            assertEquals(1, patientsWithAnemicSyndrome.size());
//            assertTrue(patientsWithAnemicSyndrome.contains(p1));
//
//            //System.out.println(patientUnit.getTestString());
//
//
//        } finally {
//            instance.close();
//        }
//    }

}
