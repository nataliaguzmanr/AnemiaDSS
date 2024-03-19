import diagnosis.*;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;

import java.time.LocalDate;
import java.util.List;

public class Main {
//.
    public static void main(String[] args) {
        Patient   p1 = new Patient(1, "Paco", 58, Gender.MALE);
        Symptom s1 = new Symptom(1, 9, "Hb", p1);
        p1.addSymptom(s1);

        Float val = p1.detectSymptomValue("Hb");
        System.out.println(val);

        Gender g= p1.getGender();
        System.out.println(g);

        boolean b = checkGender(g);
        System.out.println(b);
    }

    public static boolean checkGender(Gender g){
        if (g.equals(Gender.MALE)){
            return true;
        }else{
            return false;
        }
    }


}
