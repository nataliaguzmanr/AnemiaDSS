package diagnosis;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Patient {
    private final Integer id;
    private String name;
    private Integer age;
    private Gender gender;

    //-----------------------------
    private List<MedicalStaff> medicalStaff;
    private List<Sign> signs;
    private List<Anemia> anemias;
    //-----------------------------


    public Patient(Integer id, String name, Integer age, Gender gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.medicalStaff = new LinkedList<MedicalStaff>();
        this.signs = new LinkedList<Sign>();
        this.anemias = new LinkedList<Anemia>();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public List<MedicalStaff> getMedicalStaff() {
        return medicalStaff;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setMedicalStaff(List<MedicalStaff> medicalStaff) {
        this.medicalStaff = medicalStaff;
    }

    public List<Sign> getSigns() {
        return signs;
    }

    public void setSigns(List<Sign> signs) {
        this.signs = signs;
    }

    public List<Anemia> getAnemias() {
        return anemias;
    }

    public void setAnemias(List<Anemia> anemias) {
        this.anemias = anemias;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", medicalStaff=" + medicalStaff +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) && Objects.equals(name, patient.name) && Objects.equals(age, patient.age) && gender == patient.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, gender);
    }
}
