package utilities;

import POJOS.AnemiaType;
import org.junit.Test;

import java.util.List;

import static utilities.InputException.getFloat;

public class ExcelTest {

    @Test
    public void readExcel(){
        List<Float> testWeights = ReadExcel.readWeights(AnemiaType.ANEMIC_SYNDROME);
    }


}
