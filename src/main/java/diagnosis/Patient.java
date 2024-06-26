package diagnosis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.hash;


public class Patient implements Serializable {

    private static final long serialVersionUID = 2141646251721739510L;
    //anadir private final Integer id
    private  Integer id;
    private String name;
    private Integer age;
    private Gender gender;
    private Float weight;
    private List<MedicalStaff> medicalStaff;
    private List<Symptom> symptomsList;
    private List<Anemia> anemiasList;

    public Patient(Integer id, String name, Integer age, Gender gender, Float weight ) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.symptomsList = new ArrayList<>();
        this.anemiasList = new ArrayList<>();
    }

    public Patient(String name, Integer age, Gender gender, Float weight) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.symptomsList = new ArrayList<>();
        this.anemiasList = new ArrayList<>();
    }
    public Patient(String name, Integer age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.symptomsList = new ArrayList<>();
        this.anemiasList = new ArrayList<>();
    }
    public Patient(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public List<Anemia> getAnemiasList() {
        return anemiasList;
    }

    public List<Symptom> getSymptomsList() {
        return symptomsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(getId(), patient.getId()) && Objects.equals(getName(), patient.getName()) && Objects.equals(getAge(), patient.getAge()) && getGender() == patient.getGender() &&  Objects.equals(medicalStaff, patient.medicalStaff) && Objects.equals(symptomsList, patient.symptomsList) && Objects.equals(anemiasList, patient.anemiasList);
    }

    @Override
    public int hashCode() {
        return hash(getId(), getName(), getAge(), getGender());
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", weight=" + weight +
                '}';
    }

    public void addAnemia(Anemia anemia) {
        this.anemiasList.add(anemia);
    }

    public void addSymptom(Symptom symptom) {
        this.symptomsList.add(symptom);
    }

    public Float detectSymptomValue(String name){

        for (int i = 0; i< symptomsList.size(); i++){
            Symptom symp= symptomsList.get(i);

            if(name.equalsIgnoreCase(symp.getName())) {
                return symp.getValue();
            }
        }
        return null;
    }



}
