package utilities;

import diagnosis.*;


import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Utilities {

    public static List<Boolean> equalsSymptomCondition(List<Symptom> symtomsList, List<Condition> conditionsList){

        List<Boolean> booleanList = new LinkedList<Boolean>();
        //inizialize list w/false same length as conditions
        for (int n=0; n<conditionsList.size();n++){
            booleanList.add(n,false);
        }
        //System.out.println("\nFalse inizialized list "+booleanList);
        //System.out.println(booleanList.size());


        for(int i=0; i<symtomsList.size(); i++){
            for (int j=0; j<conditionsList.size(); j++){ //ordered conditions

                String sympName = symtomsList.get(i).getName();
                String condName = conditionsList.get(j).getName();

                String condSign =  conditionsList.get(j).getSign();

                Float condValue1 = conditionsList.get(j).getValue1();
                Float condValue2 = conditionsList.get(j).getValue1();
                Float sympValue = symtomsList.get(i).getValue();


                if(sympName.equalsIgnoreCase(condName)){
                    //System.out.println(sympName);

                    if(sympValue == null){
                        booleanList.set(j,false);
                        continue;
                    }
                    else if(condSign.equalsIgnoreCase(">")){
                        if (sympValue>condValue1){
                            booleanList.set(j,true);
                        }

                    }else if(condSign.equalsIgnoreCase("<")){
                        if (sympValue<condValue1){
                            booleanList.set(j,true);
                        }
                    }else if(condSign.equalsIgnoreCase("entre")){
                        if (sympValue>condValue1 && sympValue<condValue2){
                            booleanList.set(j,true);
                        }

                    }else if(condSign.equalsIgnoreCase("=")){
                        if (sympValue==condValue1){
                            booleanList.set(j,true);

                        }
                    }

                }

            }
        }
        return booleanList;
    }


    public static Float getScore(Anemia anemia, List<Boolean> booleanList){

        float score=0;

        List<Float> weightsList = anemia.getWeights();
        System.out.println(weightsList);

        for (int j=0; j<booleanList.size();j++){
            if((booleanList.get(j).equals(true))){
                score = score + weightsList.get(j);
                //System.out.println(score);
                System.out.println("Weight: " +weightsList.get(j));
                }

            }
        return score;
    }



    public static String getReport(MedicalStaff medStaff, ClinicalHistory clinHist, Patient patient,
                                   List<Float> anemiaScores, List<Anemia> anemias) {
        String path = "src/main/resources/reports/";
        String fileName = "Date_"+clinHist.getSymptomsDate().toString()+"_patientID_"
                +patient.getId().toString()+".txt";

        String file = path + fileName;
        File archivo = new File(file);

        PrintWriter pw = null;
            try {
                pw = new PrintWriter(archivo);
                pw.println(medStaff.getName());
                pw.println(clinHist.getSymptomsDate().toString());
                pw.println(patient.toString());
                pw.print("\n");

                for(int i=0; i<anemiaScores.size(); i++){
                    pw.println(anemiaScores.get(i)+" % " + anemias.get(i).getAnemiaType().toString());
                }

                System.out.println("Report written successfully");
            } catch (IOException ioe) {
                System.out.println("Error" + ioe);
            } finally {
                if (pw != null) {
                    pw.close();
                }

            }
            return file;
    }


    public static String xor_encrypt_decrypt(String inputString, char xorKey) {

        // Define String to store encrypted/decrypted String
        StringBuilder outputString = new StringBuilder();

        // calculate length of input string
        int len = inputString.length();

        // perform XOR operation of key
        // with every character in string
        for (int i = 0; i < len; i++)
        {
            outputString.append((char) (inputString.charAt(i) ^ xorKey));
        }

        return outputString.toString();
    }


    // Read Integer in range
    public static Integer readIntFromKeyboardInRange(String question, int begin, int end) {
        System.out.println(question);
        Integer num;
        if (begin > end) {
            Integer temp = begin;
            begin = end;
            end = begin;
        }
        while (true) {
            try {
                String s = null;
                BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
                s = bf.readLine();
                num = Integer.parseInt(s);
                if (num >= begin && num <= end) {
                    return num;
                } else {
                    System.out.println("The number must be between " + begin + " and " + end + ".");
                }
            } catch (IOException ioe) {
                System.out.println("Error. Re-enter a number");
            } catch (NumberFormatException nfe) {
                System.out.println("You have not entered a number. Enter an integer number.");
            }
        }
    }



}
