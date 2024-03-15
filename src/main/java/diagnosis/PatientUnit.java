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
    private final Set<Patient> patientsWithIronDeficiencyAnemia;
    private final Set<Patient> patientsWithMegaloblasticAnemia;
    private final Set<Patient> patientsWithAplasticAnemia;
    private final Set<Patient> patientsWithChronicDiseaseAnemia;
    private final Set<Patient> patientsWithPolycythemia;
    private final Set<Patient> patientsMALE;



    public PatientUnit() {
        this(DataSource.createStore());
    }

    public PatientUnit(DataStore<Patient> patients) {
        this.patients = patients;
        this.patientsWithAnemicSyndrome = new HashSet<>();
        this.patientsWithPosthemorragicAnemia = new HashSet<>();
        this.patientsWithHemolyticAnemia = new HashSet<>();
        this.patientsWithIronDeficiencyAnemia = new HashSet<>();
        this.patientsWithMegaloblasticAnemia = new HashSet<>();
        this.patientsWithAplasticAnemia = new HashSet<>();
        this.patientsWithChronicDiseaseAnemia = new HashSet<>();
        this.patientsWithPolycythemia = new HashSet<>();
        this.patientsMALE = new HashSet<>();

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
        return this.patientsWithIronDeficiencyAnemia;
    }
    public Set<Patient> getPatientsWithMegaloblasticAnemia(){
        return this.patientsWithMegaloblasticAnemia;
    }
    public Set<Patient> getPatientsWithAplasticAnemia(){
        return this.patientsWithAplasticAnemia;
    }

    public Set<Patient> getPatientsWithChronicDiseaseAnemia(){
        return this.patientsWithChronicDiseaseAnemia;
    }

    public Set<Patient> getPatientsWithPolycythemia(){
        return this.patientsWithPolycythemia;
    }


    public Set<Patient> getPatientsMALE() {
        return this.patientsMALE;
    }

    public String getTestString() {
        return testString;
    }
    public void setTestString(String testString) {
        this.testString = testString;
    }


}
