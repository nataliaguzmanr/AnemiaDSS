package utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

    public static List<Float> readWeights() {

        ///utilizar interfaces: devolver List, no LinkedList (para no centrarnos solo en esta implementación)

        File excelFile = new File(Constants.CEFilePath);
        List<Float> weightList = new LinkedList<Float>();

        // we create an XSSF Workbook object for our XLSX Excel File
        //It is a class that is used to represent both high and low level Excel file formats.
        try (FileInputStream fis = new FileInputStream(excelFile); // we create an XSSF Workbook object for our XLSX Excel File
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            // we get first sheet
            XSSFSheet sheet = workbook.getSheetAt(0);   //Get the HSSFSheet object at the given index
            int totalRows = sheet.getPhysicalNumberOfRows();
            System.out.println("READING " + (totalRows - 1) + " compounds\n");

            Iterator<Row> rowIt = sheet.iterator();
            // skip the header
            rowIt.next();   //lee la linea en la que pone "Patrones"
            //rowIt.next(); //se salta la linea en la que pone COMPOUND, FORMULA...

            //creamos todas las variables que vamos a ir necesitando
            String name;
            String INCHI;
            String RefHMDB;
            Integer RefPubChem;
            String formula;
            Double M;
            Double m_z;
            Double MTcompnd;    //datos no primitivos para poder asignar null
            Double MTmets;
            Double RMTmets;
            Double MTmes;
            Double RMTmes;
            Double eff_mobility;

            while (rowIt.hasNext()) {   //este bucle va avanzando linea a linea

                Row row = rowIt.next();

                //iterate on cells for the current row
                Iterator<Cell> cellIterator = row.cellIterator();   //con este iterador nos vamos moviendo por las columnas dentro de una misma fila

                //vamos leyendo cada columna y guardando los valores en una variable para luego crear los metabolitos
                name = cellIterator.next().getStringCellValue();

                //para algunas de las siguientes propiedades, hay algunas que están sin rellenar, en tal caso, se asigna un valor imposible
                try {
                    INCHI = cellIterator.next().getStringCellValue();
                } catch (IllegalStateException ise) {
                    INCHI = null;
                }
                try {
                    RefHMDB = cellIterator.next().getStringCellValue();
                    if (RefHMDB == "") {
                        RefHMDB = null;
                    }
                } catch (IllegalStateException ise) {
                    RefHMDB = null;
                }
                try {
                    RefPubChem = (int) cellIterator.next().getNumericCellValue();
                    if (RefPubChem == 0) {
                        RefPubChem = null;
                    }
                } catch (IllegalStateException ise) {
                    RefPubChem = null;
                }
                try {
                    formula = cellIterator.next().getStringCellValue();
                } catch (IllegalStateException ise) {
                    formula = null;
                }

                M = cellIterator.next().getNumericCellValue();
                m_z = cellIterator.next().getNumericCellValue();

                try {
                    MTcompnd = cellIterator.next().getNumericCellValue();   //este método devuelve un double, por lo tanto no hay que hacer ningín cast
                } catch (IllegalStateException ise) {
                    MTcompnd = null;
                }

                try {
                    MTmets = cellIterator.next().getNumericCellValue();
                } catch (IllegalStateException ise) {
                    MTmets = null;
                }

                try {
                    RMTmets = cellIterator.next().getNumericCellValue();
                } catch (IllegalStateException ise) {
                    RMTmets = null;
                }

                try {
                    MTmes = cellIterator.next().getNumericCellValue();
                } catch (IllegalStateException ise) {
                    MTmes = null;
                }

                try {
                    RMTmes = cellIterator.next().getNumericCellValue();
                } catch (IllegalStateException ise) {
                    RMTmes = null;
                }

                try {
                    eff_mobility = cellIterator.next().getNumericCellValue();   //este método devuelve un double, por lo tanto no hay que hacer ningín cast
                } catch (IllegalStateException ise) {
                    eff_mobility = null;
                }
                //ahora leemos los fragmentos como String y luego con Split lo separamos en cada Double correspondiente
                String celdaLeida;
                String[] fragmentsLeidos;   //el método split devuelve un array de Strings
                List<Fragment> fragments = new LinkedList<Fragment>();
                celdaLeida = cellIterator.next().getStringCellValue();

                if (celdaLeida.equalsIgnoreCase("") || celdaLeida.equalsIgnoreCase("XXX")) {    //si lee esto es porque no hay fragments
                    fragments = null;
                    //también podemos llamar al constructor que no recibe fragmentos
                    //Metabolito m = new Metabolito(name, formula, M, m_z, MTcompnd, MTmets, RMTmets, MTmes, RMTmes);

                } else {    //en caso de que si que lea algo, se añaden a la lista

                    fragmentsLeidos = celdaLeida.split("[,;]");    //los fragmentos estan separados por comas
                    for (String s : fragmentsLeidos) {  //recorremos cada uno de los fragmentos (todavia en Strings)

                        try {       //si no se puede meter, es porque no solo hay numeros
                            fragments.add(new Fragment(Double.parseDouble(s)));   //transformamos el String en Double

                        } catch (NumberFormatException e) {    //si llegamos al catch es porque hay texto a parte de numeros
                            String[] sinProblemas = s.split("[(]");     //tenemos que quedarnos con la parte que no contiene texto y que viene entre ()
                            fragments.add(new Fragment(Double.parseDouble(sinProblemas[0])));
                        }
                    }

                }

                //vamos creando cada metabolito y lo añadimos a la lista
                Metabolito m = new Metabolito(name, INCHI, RefHMDB, RefPubChem, formula, M, m_z, MTcompnd, MTmets, RMTmets, MTmes, RMTmes, fragments, eff_mobility);
                metabolitos.add(m);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        }
        return metabolitos;
    }

}
