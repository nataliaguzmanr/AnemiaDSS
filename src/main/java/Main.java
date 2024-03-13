import diagnosis.*;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;

import java.time.LocalDate;
import java.util.List;

public class Main {
//.
    public static void main(String[] args) {
        Patient   p1 = new Patient(1, "Paco", 58, Gender.MALE, LocalDate.of(1987,10,12));
        Symptom s1 = new Symptom(1, 9, "Hb", p1);
        p1.addSymptom(s1);

        Float val = p1.detectSymptomValue("Hb");
        System.out.println(val);
    }
}
