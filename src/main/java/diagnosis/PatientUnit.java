package diagnosis;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;

import java.util.HashSet;
import java.util.Set;

public class PatientUnit implements RuleUnitData {

    private final DataStore<Patient> patients;
    private String testString;
    private final Set<Patient> patientsWithAnemicSyndrome;


    public PatientUnit() {
        this(DataSource.createStore());
    }

    public PatientUnit(DataStore<Patient> patients) {
        this.patients = patients;
        this.patientsWithAnemicSyndrome = new HashSet<>();
    }

    public DataStore<Patient> getPatients() {
        return patients;
    }

    public Set<Patient> getPatientsWithAnemicSyndrome() {
        return this.patientsWithAnemicSyndrome;
    }

    public String getTestString() {
        return testString;
    }
    public void setTestString(String testString) {
        this.testString = testString;
    }


}
