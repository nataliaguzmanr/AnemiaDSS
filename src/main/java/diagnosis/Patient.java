package diagnosis;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Patient {
    private final Integer id;
    private String name;
    private Integer age;
    private Gender gender;
    private LocalDate date;
    private List<MedicalStaff> medicalStaff;
    private List<Symptom> symptoms;
    private List<Anemia> anemias;

    public Patient(Integer id, String name, Integer age, Gender gender, LocalDate date) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.date = date;
    }

    public Integer getId() {
        return id;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(getId(), patient.getId()) && Objects.equals(getName(), patient.getName()) && Objects.equals(getAge(), patient.getAge()) && getGender() == patient.getGender() && Objects.equals(getDate(), patient.getDate()) && Objects.equals(medicalStaff, patient.medicalStaff) && Objects.equals(symptoms, patient.symptoms) && Objects.equals(anemias, patient.anemias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge(), getGender(), getDate(), medicalStaff, symptoms, anemias);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", date=" + date +
                ", medicalStaff=" + medicalStaff +
                ", symptoms=" + symptoms +
                ", anemias=" + anemias +
                '}';
    }

    public void addAnemia(Anemia anemia) {
        this.anemias.add(anemia);
    }

    public void addSymptom(Symptom symptom) {
        this.symptoms.add(symptom);
    }

    public void detectSymptom(String name){


    }






}
