package diagnosisTests;

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
        condListTest.add(c1_ap); //15.56
        condListTest.add(c0);
        condListTest.add(c0);
        condListTest.add(c2_ap); //17.78
        condListTest.add(c0);
        condListTest.add(c0);
        condListTest.add(c3_ap); //20.0
        condListTest.add(c0);
        condListTest.add(c0);
        condListTest.add(c0);
        condListTest.add(c0);
        condListTest.add(c0);
        condListTest.add(c0);
        condListTest.add(c0);
        condListTest.add(c4_ap); //13.34
        condListTest.add(c5_ap); //6.66
        condListTest.add(c6_ap); //11.12
        condListTest.add(c0);
        condListTest.add(c0);
        condListTest.add(c7_ap);//8.88
        condListTest.add(c0);
        condListTest.add(c0);
        condListTest.add(c0);

        System.out.println(condListTest);

        Symptom s1 = new Symptom(7.9F, "Hb");
        Symptom s2 = new Symptom(1F, "Headache");
        Symptom s3 = new Symptom(19.3F, "EPO");
        Symptom s4 = new Symptom(7.2F, "Plaquets");
        Symptom s5 = new Symptom(4.3F, "RBC");

        List<Symptom> symptomListTest = new LinkedList<>();
        symptomListTest.add(s1);
        symptomListTest.add(s2);
        symptomListTest.add(s3);
        //symptomListTest.add(s4);
        symptomListTest.add(s5);
        System.out.println(symptomListTest);

        Utilities u = new Utilities();

        List<Boolean> bList;
        bList = u.equalsSymptomCondition(symptomListTest, condListTest);
        System.out.println(bList);

        List<Float> weithtsList = new LinkedList();
        weithtsList.add(15.56F);
        weithtsList.add(0F);
        weithtsList.add(0F);
        weithtsList.add(20F);
        weithtsList.add(0F);
        weithtsList.add(0F);
        weithtsList.add(0F);
        weithtsList.add(0F);
        weithtsList.add(0F);
        weithtsList.add(0F);
        weithtsList.add(0F);
        weithtsList.add(13.34F);
        weithtsList.add(6.66F);
        weithtsList.add(11.12F);
        weithtsList.add(0F);
        weithtsList.add(0F);
        weithtsList.add(8.88F);
        weithtsList.add(0F);
        weithtsList.add(0F);
        weithtsList.add(0F);


        Anemia a = new Anemia(1, AnemiaType.APLASTIC_ANEMIA, weithtsList, condListTest);

        float ap_score = u.getScore(a,bList);
        System.out.println("SCORE: "+ap_score);


    }



}
