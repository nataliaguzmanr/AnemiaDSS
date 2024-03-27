import diagnosis.Gender;
import jdbc.JDBCManager;
import jpa.JPAUserManager;

import static utilities.InputException.getFloatSympTRUE;

public class Main {
//.
    public static void main(String[] args) {
        System.out.println("Esto es el main");
        //Desde aquí vamos a crear la base de datos
        //JDBCManager jdbcManager = new JDBCManager();
        JPAUserManager jpaUserManager = new JPAUserManager();

    }

    public static boolean checkGender(Gender g){
        if (g.equals(Gender.MALE)){
            return true;
        }else{
            return false;
        }
    }



}
