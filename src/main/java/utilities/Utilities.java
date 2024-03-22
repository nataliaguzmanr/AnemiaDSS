package utilities;

import diagnosisTests.Anemia;
import diagnosisTests.Condition;
import diagnosisTests.Symptom;

import java.util.LinkedList;
import java.util.List;

public class Utilities {

    public List<Boolean> equalsSymptomCondition(List<Symptom> symtomsList, List<Condition> conditionsList){


        List<Boolean> booleanList = new LinkedList<Boolean>();
        //inizialize list w/false same length as conditions
        for (int n=0; n<conditionsList.size();n++){
            booleanList.add(n,false);
        }
        System.out.println("\nFalse inizialized list "+booleanList);


        for(int i=0; i<symtomsList.size(); i++){
            for (int j=0; j<conditionsList.size(); j++){ //ordered conditions

                String sympName = symtomsList.get(i).getName();
                String condName = conditionsList.get(j).getName();

                String condSign =  conditionsList.get(j).getSign();

                float condValue1 = conditionsList.get(j).getValue1();
                float condValue2 = conditionsList.get(j).getValue1();
                float sympValue = symtomsList.get(i).getValue();


                if(sympName.equalsIgnoreCase(condName)){

                    if(condSign.equalsIgnoreCase(">")){

                        if (sympValue>condValue1){
                            //return true;
                            booleanList.add(j,true);
                        }/*else{
                            //return false;
                            booleanList.add(j,false);
                        }*/

                    }else if(condSign.equalsIgnoreCase("<")){
                        if (sympValue<condValue1){
                            //return true;
                            booleanList.add(j,true);
                        }/*else{
                            //return false;
                            booleanList.add(j,false);
                        }*/
                    }else if(condSign.equalsIgnoreCase("entre")){
                        if (sympValue>condValue1 && sympValue<condValue2){
                            //return true;
                            booleanList.add(j,true);
                        }/*else{
                            //return false;
                            booleanList.add(j,false);
                        }*/

                    }else if(condSign.equalsIgnoreCase("=")){
                        if (sympValue==condValue1){
                            booleanList.add(j,true);
                            //return true;
                        }/*else{
                            //return false;
                            booleanList.add(j,false);
                        }*/
                    }

                }

            }
        }

        return booleanList;
    }


    public float getScore(Anemia anemia, List<Boolean> booleanList){

        float score=0;

        List<Float> weightsList = anemia.getWeights();

        for (int i=0; i<weightsList.size();i++){
            for (int j=0; j<booleanList.size();j++){

                if( (i==j) && (booleanList.get(j).equals(true))){
                    score = score + weightsList.get(i);
                    //System.out.println(score);
                }

            }
        }

        return score;
    }
}
