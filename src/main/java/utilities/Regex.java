package utilities;


import diagnosis.Condition;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Regex {

    public static Condition getConditionFromCell(String cell){
        List<String> symbols = new ArrayList<>();
        List<String> numbers = new ArrayList<>();

        // Regular expressions
        String regexSymbols = "[<>-]";
        String regexNumbers = "\\d*\\.\\d+|\\d+";

        // Pattern objects
        Pattern patternSymbols = Pattern.compile(regexSymbols);
        Pattern patternNumbers = Pattern.compile(regexNumbers);

        // Matcher objects
        Matcher matcherSymbols = patternSymbols.matcher(cell);
        Matcher matcherNumbers = patternNumbers.matcher(cell);

        // Finding symbols
        // we are going to have just one
        String sign = null;
        while (matcherSymbols.find()) {
            sign = matcherSymbols.group();
//            System.out.println(sign);
            if (sign.equals("-")){
                sign = "entre";
            }
            //symbols.add(matcherSymbols.group());
        }

        // Finding numbers
        Float value1 = null;
        Float value2 = null;
        int contador = 0;
        while (matcherNumbers.find()) {
            if(contador==1){
                value2 = Float.valueOf(matcherNumbers.group());
//                System.out.println(value2);
            }
            value1 = Float.valueOf(matcherNumbers.group());
//            System.out.println(value1);
            contador = 1;
            //numbers.add(matcherNumbers.group());
        }

        Condition condition = new Condition(value1, value2, sign);
        return condition;
    }











   /* public static void main(String[] args) {
        String data = *//*"<8" +
                "null" +*//*
                "1" *//*+
                "10-13.5" +
                "10-12.3" +
                ">17.5"*//*;

        List<String> symbols = new ArrayList<>();
        List<String> numbers = new ArrayList<>();

        // Regular expressions
        String regexSymbols = "[<>-]";
        String regexNumbers = "\\d*\\.\\d+|\\d+";

        // Pattern objects
        Pattern patternSymbols = Pattern.compile(regexSymbols);
        Pattern patternNumbers = Pattern.compile(regexNumbers);

        // Matcher objects
        Matcher matcherSymbols = patternSymbols.matcher(data);
        Matcher matcherNumbers = patternNumbers.matcher(data);

        // Finding symbols
        // we are going to have just one
        String sign = null;
        while (matcherSymbols.find()) {
            sign = matcherSymbols.group();
            System.out.println(sign);
            //symbols.add(matcherSymbols.group());
        }

        // Finding numbers
        Float value1 = null;
        Float value2 = null;
        int contador = 0;
        while (matcherNumbers.find()) {
            if(contador==1){
                value2 = Float.valueOf(matcherNumbers.group());
                System.out.println(value2);
            }
            value1 = Float.valueOf(matcherNumbers.group());
            System.out.println(value1);
            contador = 1;
            //numbers.add(matcherNumbers.group());
        }

    Condition condition = new Condition(value1, value2, sign);
    }*/
}



