package ui;


import POJOS.User;
import jdbc.JDBCManager;
import jpa.JPAUserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;

public class Executable{
    private static JDBCManager jdbcManager = new JDBCManager();
    private static BufferedReader bufferedReadereader = new BufferedReader(new InputStreamReader(System.in));
    private static JPAUserManager jpaUserManager = new JPAUserManager();
    private EntityManager em;
    public static void main(String[] args) throws Exception{

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
            System.out.println("Please, write your email address:");
            String id_str = bufferedReadereader.readLine();
            int id = Integer.parseInt(id_str);
            System.out.println("Please write your password:");
            String password = bufferedReadereader.readLine();

            boolean userRepeated = jpaUserManager.userNameTaken(id);
            if(userRepeated == true) {
                System.out.println("ERROR, exiting user");
            }else {

                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(password.getBytes());
                byte[] hash = md.digest();
                User user = new User(id_str, hash);
                jpaUserManager.newUser(user);
            }
        }catch(Exception e) {
            System.out.println(e.getCause());
        }
    }

    public static void login() throws Exception {
        try{
            System.out.println("Please, write your id:");
            String id_str = bufferedReadereader.readLine();
            int id = Integer.parseInt(id_str);
            // Ask the user for a password
            System.out.println("Please write your password:");
            String password = bufferedReadereader.readLine();
            User user = jpaUserManager.checkPassword(id, password);
            if (user == null) {
                System.out.println("Wrong email or password");
            }else{
                patientHandlerManu();
            }

        } catch(Exception e) {
        System.out.println(e.getCause());
        }

    }

}



