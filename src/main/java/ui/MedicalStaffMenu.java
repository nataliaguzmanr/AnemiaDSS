package ui;


import diagnosis.Anemia;
import diagnosis.AnemiaType;
import diagnosis.Condition;
import diagnosis.User;
import jdbc.JDBCManager;
import jpa.JPAUserManager;
import utilities.ReadExcel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.List;

import static ui.PatientHandlerMenu.patientHandlerMenu;
import static utilities.InputException.getString;

public class MedicalStaffMenu {
    private static JDBCManager jdbcManager = new JDBCManager();
    private static BufferedReader bufferedReadereader = new BufferedReader(new InputStreamReader(System.in));
    private static JPAUserManager jpaUserManager = new JPAUserManager();



    public static void main(String[] args) throws Exception{

        Anemia anemicSyndromeMALE = initializeAnemia(AnemiaType.ANEMIC_SYNDROME_MALE);
        Anemia anemicSyndromeFEMALE = initializeAnemia(AnemiaType.ANEMIC_SYNDROME_FEMALE);

        Anemia posthemorrhagicMALE = initializeAnemia(AnemiaType.POSTHEMORRHAGIC_ANEMIA_MALE);
        Anemia posthemorrhagicFEMALE = initializeAnemia(AnemiaType.POSTHEMORRHAGIC_ANEMIA_FEMALE);

        Anemia hemolyticMALE = initializeAnemia(AnemiaType.HEMOLYTIC_ANEMIA_MALE);
        Anemia hemolyticFEMALE = initializeAnemia(AnemiaType.HEMOLYTIC_ANEMIA_FEMALE);

        Anemia inheritedSpherocytMALE = initializeAnemia(AnemiaType.INHERITED_SPHEROCYTOSIS_MALE);
        Anemia inheritedSpherocytFEMALE = initializeAnemia(AnemiaType.INHERITED_SPHEROCYTOSIS_FEMALE);

        Anemia thalassemiaMALE = initializeAnemia(AnemiaType.THALASSEMIA_MALE);
        Anemia thalassemiaFEMALE = initializeAnemia(AnemiaType.THALASSEMIA_FEMALE);

        Anemia ironDeficiencyMALE = initializeAnemia(AnemiaType.IRON_DEFICIENCY_ANEMIA_MALE);
        Anemia ironDeficiencyFEMALE = initializeAnemia(AnemiaType.IRON_DEFICIENCY_ANEMIA_FEMALE);

        Anemia megaloblasticMALE = initializeAnemia(AnemiaType.MEGALOBLASTIC_ANEMIA_MALE);
        Anemia megaloblasticFEMALE = initializeAnemia(AnemiaType.MEGALOBLASTIC_ANEMIA_FEMALE);

        Anemia aplasicMALE = initializeAnemia(AnemiaType.APLASIC_ANEMIA_MALE);
        Anemia aplasicFEMALE = initializeAnemia(AnemiaType.APLASIC_ANEMIA_FEMALE);

        Anemia chronicDiseaseMALE = initializeAnemia(AnemiaType.CHRONIC_DISEASE_ANEMIA_MALE);
        Anemia chronicDiseaseFEMALE = initializeAnemia(AnemiaType.CHRONIC_DISEASE_ANEMIA_FEMALE);

        Anemia polycythemiaMALE = initializeAnemia(AnemiaType.POLYCYTHEMIA_MALE);
        Anemia polycythemiaFEMALE = initializeAnemia(AnemiaType.POLYCYTHEMIA_FEMALE);

        welcomeMenu();
    }

    public static Anemia initializeAnemia(AnemiaType anemiaType){

        List<Float> weightsList = ReadExcel.readWeights(anemiaType);
        List<Condition> conditionList = ReadExcel.readConditions(anemiaType);
        Anemia anemia = new Anemia(anemiaType,weightsList,conditionList);

        return anemia;
    }

    public static void welcomeMenu(){
        System.out.println("Choose an option:");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("0. Exit");

        int choice = 0;
        try {
            choice = Integer.parseInt(bufferedReadereader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        switch (choice) {
            case 1:
                register();
                break;
            case 2:
                login();
                break;
            case 0:
                System.out.println("Data base closed");
                jdbcManager.disconnect();
                jpaUserManager.disconnect();
                System.exit(0);
                break;
            default:
                break;
        }
    }

public static void register(){
        try {

            String userName = getString("Please, write your USER NAME:");
            String password = getString("Please write your password:");

            boolean userRepeated = jpaUserManager.userNameTaken(userName);

            if(userRepeated == true) {
                System.out.println("ERROR, exiting user");
            }else {

                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(password.getBytes());
                byte[] hash = md.digest();
                User user = new User(userName, hash);
                //jpaUserManager.newUser(user);
            }
        }catch(Exception e) {
            System.out.println(e.getCause());
        }
    }


public static void login() {
        try{
            String userName = getString("Please, write your USER NAME:");
            String password = getString("Please, write your password:");
            User user = jpaUserManager.checkPassword(userName, password);
            if (user == null) {
                System.out.println("Wrong email or password");
                welcomeMenu();
            }else{
                patientHandlerMenu();
            }

        } catch(Exception e) {
        System.out.println(e.getCause());
        }

    }


}



