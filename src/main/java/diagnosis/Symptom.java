package diagnosis;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.hash;

public class Symptom implements Serializable {

    private static final long serialVersionUID = 4970354669582451896L;
    // ANADIR private final Integer id;
    private Integer id;
    private Float value;
    private String name;
    private LocalDate symptom_date;
    private Patient patient;


    public Symptom(Integer id, float value, String name, Patient patient, LocalDate symptom_date) {
        this.id = id;
        this.value = value;
        this.name = name;
        this.symptom_date = symptom_date;
        this.patient = patient;
    }

    public Symptom(float value, String name, Patient patient) {
        this.value = value;
        this.name = name;
        this.patient = patient;
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

    public LocalDate getDate() {
        return symptom_date;
    }

    public void setDate(LocalDate symptom_date) {
        this.symptom_date = symptom_date;
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
        return Objects.equals(id, symptom.id) && Objects.equals(value, symptom.value) && Objects.equals(name, symptom.name) && Objects.equals(symptom_date, symptom.symptom_date) && Objects.equals(patient, symptom.patient);
    }

    @Override
    public int hashCode() {
        return hash(getId(), getValue(), getName(), getPatient());
    }

    @Override
    public String toString() {
        return "Symptom{" +
                //"id=" + id +
                ", value=" + value +
                ", name='" + name  +
                ", patient=" + patient +
                '}';
    }


}
