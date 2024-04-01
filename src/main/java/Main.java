import diagnosis.AnemiaType;
import diagnosis.Gender;
import utilities.ReadExcel;

import java.util.List;

public class Main {
//.
    public static void main(String[] args) {
        //System.out.println("Esto es el main");
        //JDBCManager manager = new JDBCManager();
        List<Float> weightsList = ReadExcel.readWeights(AnemiaType.APLASTIC_ANEMIA_FEMALE);
        System.out.println(weightsList);
//        List<Condition> conditionList = ReadExcel.readConditions(AnemiaType.APLASIC_ANEMIA_FEMALE);

    }

    public static boolean checkGender(Gender g){
        if (g.equals(Gender.MALE)){
            return true;
        }else{
            return false;
        }
    }



}
