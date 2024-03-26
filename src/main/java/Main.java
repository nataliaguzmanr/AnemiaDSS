import diagnosis.Gender;

import static utilities.InputException.getFloatSympTRUE;

public class Main {
//.
    public static void main(String[] args) {
        System.out.println("Esto es el main");
/*        Patient p1 = new Patient(1, "Paco", 58, Gender.MALE);
        Symptom s1 = new Symptom(1, 9, "Hb", p1);
        p1.addSymptom(s1);


        Float val = p1.detectSymptomValue("Hb");
        System.out.println(val);

        Gender g = p1.getGender();
        System.out.println(g);

        boolean b = checkGender(g);
        System.out.println(b);*/

        //JDBCManager jdbcMan = new JDBCManager();
        //esto es solo un comentario

        //List<Float> testWeights = ReadExcel.readWeights(AnemiaType.ANEMIC_SYNDROME);

        Float floatException = getFloatSympTRUE("Insert value TRUE/FALSE");
        System.out.println(floatException);


        //-----------------------------------------------------------------------------------------


    }

    public static boolean checkGender(Gender g){
        if (g.equals(Gender.MALE)){
            return true;
        }else{
            return false;
        }
    }



}
