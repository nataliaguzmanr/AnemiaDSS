package utilities;

import POJOS.*;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class UtilitiesTest {

    @Test
    public void readExcel(){
        //List<Float> testWeights = ReadExcel.readWeights(AnemiaType.POLYCYTHEMIA);
        List<Condition> testConditions = ReadExcel.readConditions(AnemiaType.ANEMIC_SYNDROME);
        System.out.println(testConditions);

    }

    @Test
    public void getReport(){

        List<Float> scores = new LinkedList<>();
        scores.add(13.56F);
        scores.add(55.32F);

        List<Anemia> anemias = new LinkedList<>();
        anemias.add(new Anemia(AnemiaType.MEGALOBLASTIC_ANEMIA));
        anemias.add(new Anemia(AnemiaType.POSTHEMORRHAGIC_ANEMIA));

        MedicalStaff ms = new MedicalStaff("Maria Martinez");
        Patient p = new Patient(4, "Lola Cruz", 34, Gender.MALE, 67.4F);
        ClinicalHistory ch = new ClinicalHistory(LocalDate.now());

        File testFile = Utilities.getReport(ms, ch, p, scores, anemias);
        System.out.println(testFile);


    }


}
