package POJOS;


import java.io.Serializable;
import java.util.Objects;

import static java.util.Objects.hash;

public class Symptom implements Serializable {

    private static final long serialVersionUID = 4970354669582451896L;
    // ANADIR private final Integer id;
    private Integer id;
    private Float value;
    private String name;
    private Patient patient;


    public Symptom(float value, String name) {
        this.value = value;
        this.name = name;
    }

    public Symptom(float value, String name,  Patient patient) {
        this.value = value;
        this.name = name;
        this.patient = patient;
    }

    public Symptom(Integer id, float value, String name) {
        this.id = id;
        this.value = value;
        this.name = name;
    }


    public Symptom(Integer id, float value, String name, Patient patient) {
        this.id = id;
        this.value = value;
        this.name = name;
        this.patient = patient;
    }

    public Integer getId() {
        return id;
    }

    public Float getValue() {
        return value;
    }

    public String getName() {
        return name;
    }


    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symptom symptom = (Symptom) o;
        return Objects.equals(id, symptom.id)  && Objects.equals(patient, symptom.patient);
    }

    @Override
    public int hashCode() {
        return hash(getId(), getValue(), getName(), getPatient());
    }

    @Override
    public String toString() {
        return "\nSymptom{" +
                "id=" + id +
                ", value=" + value +
                ", name='" + name + '\'' +
                ", patient=" + patient +
                '}';
    }
}
