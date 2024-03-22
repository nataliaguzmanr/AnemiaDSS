package diagnosisTests;

import org.drools.ruleunits.api.DataStore;

public class AnemiaUnit {

    private final DataStore<Anemia> anemiaDataStore;

    private String testString;

    public AnemiaUnit(DataStore<Anemia> anemiaDataStore) {
        this.anemiaDataStore = anemiaDataStore;
    }


    public DataStore<Anemia> getAnemiaDataStore() {
        return anemiaDataStore;
    }

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }
}
