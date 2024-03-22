package diagnosisTest;

import diagnosis.Anemia;
import diagnosis.AnemiaType;
import diagnosis.Condition;
import diagnosis.Symptom;
import org.junit.Test;
import utilities.Utilities;


import java.util.LinkedList;
import java.util.List;

public class ConditionSymtomTest {

    @Test
    public void testEqualSympCOND() {

        Condition c1_ap = new Condition("Plaquets", 7.4F,0F,"<");
        Condition c0 = new Condition ("null", 0F);
        Condition c2_ap = new Condition("Leukocytes", 4.5F, 0F, "<");
        Condition c3_ap = new Condition("RBC", 4.5F, 0F, "<");
        Condition c4_ap = new Condition("Skin paleness", 1F, 0F, "=");
        Condition c5_ap = new Condition("Headache", 1F, 0F, "=");
        Condition c6_ap = new Condition("Fatigue", 1F, 0F, "=");
        Condition c7_ap = new Condition("Dyspnea", 1F, 0F, "=");

        List<Condition> condListTest = new LinkedList<>();
        condListTest.add(c1_ap); //15.56 -> Plaquets
        condListTest.add(c0);
        condListTest.add(c0);
        condListTest.add(c2_ap); //17.78 -> leukocytes
        condListTest.add(c0);
        condListTest.add(c0);
        condListTest.add(c3_ap); //20.0 -> RBC
        condListTest.add(c0);
        condListTest.add(c0);
        condListTest.add(c0);
        condListTest.add(c0);
        condListTest.add(c0);
        condListTest.add(c0);
        condListTest.add(c0);
        condListTest.add(c4_ap); //13.34 -> Skin paleness
        condListTest.add(c5_ap); //6.66 -> Headache
        condListTest.add(c6_ap); //11.12 -> Fatigue
        condListTest.add(c0);
        condListTest.add(c0);
        condListTest.add(c7_ap);//8.88 -> Dyspnea
        condListTest.add(c0);
        condListTest.add(c0);
        condListTest.add(c0);

        System.out.println(condListTest);
        System.out.println("Conditions list "+condListTest.size());

        Symptom s1 = new Symptom(7.9F, "Hb");
        Symptom s2 = new Symptom(1F, "Headache");
        Symptom s3 = new Symptom(19.3F, "EPO");
        Symptom s4 = new Symptom(7.2F, "Plaquets");
        Symptom s5 = new Symptom(4.3F, "RBC");

        List<Symptom> symptomListTest = new LinkedList<>();
        symptomListTest.add(s1);
        symptomListTest.add(s2);
        symptomListTest.add(s3);
        symptomListTest.add(s4);
        symptomListTest.add(s5);
        System.out.println(symptomListTest);

        Utilities u = new Utilities();

        List<Boolean> bList;
        bList = u.equalsSymptomCondition(symptomListTest, condListTest);
        System.out.println(bList);
        System.out.println("Boolean list "+bList.size());

        List<Float> weithtsList = new LinkedList();
        weithtsList.add(15.56F); // Plaquets
        weithtsList.add(0F);
        weithtsList.add(0F);
        weithtsList.add(17.78F); //Leukocytes
        weithtsList.add(0F);
        weithtsList.add(0F);
        weithtsList.add(20F); //RBC
        weithtsList.add(0F);
        weithtsList.add(0F);
        weithtsList.add(0F);
        weithtsList.add(0F);
        weithtsList.add(0F);
        weithtsList.add(0F);
        weithtsList.add(0F);
        weithtsList.add(13.34F); //Skin paleness
        weithtsList.add(6.66F); //Headache
        weithtsList.add(11.12F); //fatigue
        weithtsList.add(0F);
        weithtsList.add(0F);
        weithtsList.add(8.88F); //dyspnea
        weithtsList.add(0F);
        weithtsList.add(0F);
        weithtsList.add(0F);
        System.out.println("Weights list "+weithtsList.size());

        Anemia a = new Anemia(1, AnemiaType.APLASTIC_ANEMIA, weithtsList, condListTest);

        float ap_score = u.getScore(a,bList);
        System.out.println("\nSCORE: "+ap_score);


    }



}
