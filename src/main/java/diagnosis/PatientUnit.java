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
    private final Set<Patient> patientsWithPosthemorragicAnemia;
    private final Set<Patient> patientsWithHemolyticAnemia;
    private final Set<Patient> patientsIronDeficiencyAnemia;
    private final Set<Patient> gender;



    public PatientUnit() {
        this(DataSource.createStore());
    }

    public PatientUnit(DataStore<Patient> patients) {
        this.patients = patients;
        this.patientsWithAnemicSyndrome = new HashSet<>();
        this.patientsWithPosthemorragicAnemia = new HashSet<>();
        this.patientsWithHemolyticAnemia = new HashSet<>();
        this.patientsIronDeficiencyAnemia = new HashSet<>();
        this.gender = new HashSet<>();

    }

    public DataStore<Patient> getPatients() {
        return patients;
    }

    public Set<Patient> getPatientsWithAnemicSyndrome() {
        return this.patientsWithAnemicSyndrome;
    }
    public Set<Patient> getPatientsWithPosthemorrhagicAnemia() {
        return this.patientsWithPosthemorragicAnemia;
    }

    public Set<Patient> getPatientsWithHemolyticAnemia() {
        return this.patientsWithHemolyticAnemia;
    }
    public Set<Patient> getPatientsWithIronDeficiencyAnemia(){
        return this.patientsIronDeficiencyAnemia;
    }

    public Set<Patient> getGender() {
        return this.gender;
    }

    public String getTestString() {
        return testString;
    }
    public void setTestString(String testString) {
        this.testString = testString;
    }


}
