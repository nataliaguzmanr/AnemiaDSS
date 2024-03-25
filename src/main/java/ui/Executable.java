package ui;


import POJOS.User;
import jdbc.JDBCManager;
import jpa.JPAUserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;

import static ui.PatientHandlerMenu.patientHandlerMenu;
import static utilities.InputException.getInt;
import static utilities.InputException.getString;

public class Executable{
    private static JDBCManager jdbcManager = new JDBCManager();
    private static BufferedReader bufferedReadereader = new BufferedReader(new InputStreamReader(System.in));
    private static JPAUserManager jpaUserManager = new JPAUserManager();
    public static void main(String[] args) throws Exception{

        welcomeMenu();
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
                jpaUserManager.newUser(user);
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



