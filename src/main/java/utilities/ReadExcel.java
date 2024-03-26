package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import diagnosis.AnemiaType;
import diagnosis.Condition;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

    public static List<Float> readWeights(AnemiaType anemiaType) {

        ///utilizar interfaces: devolver List, no LinkedList (para no centrarnos solo en esta implementación)

        String filePath = "src/main/resources/files/Weights.xlsx";
        //String filePath = "C:\\Users\\maria\\Downloads\\Weights.xlsx";
        System.out.println(filePath);
        File excelFile = new File(filePath);

        List<Float> weightList = new LinkedList<Float>();

        // we create an XSSF Workbook object for our XLSX Excel File
        //It is a class that is used to represent both high and low level Excel file formats.
        // we create an XSSF Workbook object for our XLSX Excel File
        try (FileInputStream fis = new FileInputStream(excelFile);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            // we get first sheet
            XSSFSheet sheet = workbook.getSheetAt(0);   //Get the HSSFSheet object at the given index
            int totalRows = sheet.getPhysicalNumberOfRows();
//            System.out.println("READING " + (totalRows - 1) + " types of anemia\n");

            Iterator<Row> rowIt = sheet.iterator();
            // skip the header
            rowIt.next();   //lee la linea en la que pone "ANEMIA/SYMTOMS"

            String anemiaTypeRead;
            List<Float> weightsList = new LinkedList<>();
            double cellWeight;

            while (rowIt.hasNext()) {   //este bucle va avanzando linea a linea

                Row row = rowIt.next();

                //iterate on cells for the current row: reads columns
                Iterator<Cell> cellIterator = row.cellIterator();   //con este iterador nos vamos moviendo por las columnas dentro de una misma fila

                //vamos leyendo cada columna y guardando los valores en una variable para luego crear los metabolitos
                anemiaTypeRead = cellIterator.next().getStringCellValue();
//                System.out.println(anemiaTypeRead);

                String anemiaTypeToString = anemiaType.toString();
//                System.out.println(anemiaTypeToString);
                if(anemiaTypeToString.equalsIgnoreCase(anemiaTypeRead)){

                    while(cellIterator.hasNext()){
                        cellWeight  = cellIterator.next().getNumericCellValue();
                        System.out.println(cellWeight);

                        Float cellWeightF = ((float) cellWeight);
                        weightsList.add(cellWeightF);

                    }
                    System.out.println(weightsList.size());

                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return weightList;
    }


    public static List<Condition> readConditions(AnemiaType anemiaType) {

           String filePath = "src/main/resources/files/Conditions.xlsx";
       // String filePath = "C:\\Users\\maria\\Downloads\\Conditions.xlsx";
        File excelFile = new File(filePath);

        List<Condition> conditions = new LinkedList<>();

        try (FileInputStream fis = new FileInputStream(excelFile);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheetAt(0);
            int totalRows = sheet.getPhysicalNumberOfRows();

            Iterator<Row> rowIt = sheet.iterator();

            //leemos la linea del nombre de los symptoms
            Row row = rowIt.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            //hay que saltarse la primera columna
            cellIterator.next();

            String symptom_name;
            List<String> names = new LinkedList<>();

            while(cellIterator.hasNext()){
                symptom_name  = cellIterator.next().getStringCellValue();
//                System.out.println(symptom_name);
                names.add(symptom_name);
            }

            System.out.println(names);

            String anemiaTypeRead;
            String cell;
            int contador;

            while (rowIt.hasNext()) {
                contador = 0;
                row = rowIt.next();
                cellIterator = row.cellIterator();
                anemiaTypeRead = cellIterator.next().getStringCellValue();
//                System.out.println(anemiaTypeRead);
                String anemiaTypeToString = anemiaType.toString();
//                System.out.println(anemiaTypeToString);
                if(anemiaTypeToString.equalsIgnoreCase(anemiaTypeRead)){
                    Condition c;
                    while(cellIterator.hasNext()){
                        cell  = cellIterator.next().getStringCellValue();
                        System.out.println(cell);
                        if(cell.equalsIgnoreCase("true")){
                            c = new Condition (1.F, null, "=");
                        }
                        else{
                        //llamamos al método que devuelve la Condition
                        c = Regex.getConditionFromCell(cell);
                        }

                        String name = names.get(contador);
                        c.setName(name);
                        conditions.add(c);
                        contador++;

                    }
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(conditions);
        return conditions;
    }

}
