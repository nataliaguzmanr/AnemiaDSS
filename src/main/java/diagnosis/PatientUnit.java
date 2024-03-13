package diagnosis;

import diagnosis.Patient;
import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;

import java.util.HashSet;
import java.util.Set;

public class PatientUnit implements RuleUnitData {

    private final DataStore<Patient> patients;
    private String testString;
    private final Set<Patient> patientsWithAnemicSyndrome;
    private final Set<Patient> patientsWithPosthemorragicAnemia;


    public PatientUnit() {
        this(DataSource.createStore());
    }

    public PatientUnit(DataStore<Patient> patients) {
        this.patients = patients;
        this.patientsWithAnemicSyndrome = new HashSet<>();
        this.patientsWithPosthemorragicAnemia = new HashSet<>();
    }

    public DataStore<Patient> getPatients() {
        return patients;
    }

    public Set<Patient> getPatientsWithAnemicSyndrome() {
        return this.patientsWithAnemicSyndrome;
    }
    public Set<Patient> getPatientsWithPosthemorragicAnemia() {
        return this.patientsWithPosthemorragicAnemia;
    }

    public String getTestString() {
        return testString;
    }
    public void setTestString(String testString) {
        this.testString = testString;
    }


}
