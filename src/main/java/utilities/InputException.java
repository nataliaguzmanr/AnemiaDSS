package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputException {

    private static BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
    /**
     Reads a string input from the console.
     @param question the question to be displayed to the user
     @return the string input from the user
     */
    public static String getString(String question) {

        while (true) {
            try {
                System.out.println(question);
                String leido = bufferReader.readLine();
                return leido;

            } catch (IOException ex) {
                System.out.println("\n Error. Please input again a correct value");
            }
        }
    }
    /**
     Reads an integer input from the console.
     @param str the question to be displayed to the user
     @return the integer input from the user
     */
    public static Integer getInt(String str) {
        int x = 0;
        while (true) {
            try {
                System.out.println(str);
                x = Integer.parseInt(bufferReader.readLine());
                return x;

            } catch (NumberFormatException ex) {
                System.out.println("\n Error. Please input again a correct number. ");
            } catch (IOException ex) {
                System.out.println(ex.getCause());
            }

        }
    }


    public static Float getFloat(String str) {
        float f = 0;
        while (true) {
            try {
                System.out.println(str);
                String read  = bufferReader.readLine();
                if (read.equalsIgnoreCase( "\n")){
                    f = Float.parseFloat(read);
                    return f;
                }else{
                    f = Float.parseFloat(read);
                    return f;
                }

            } /*catch (NumberFormatException ex) {
                System.out.println("\n Error. Please input again a correct number. ");
            }*/ catch (IOException ex) {
                System.out.println(ex.getCause());
            }

        }
    }


}
