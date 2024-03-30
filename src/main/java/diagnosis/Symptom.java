package diagnosis;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.hash;

public class Symptom implements Serializable {

    private static final long serialVersionUID = 4970354669582451896L;
    // ANADIR private final Integer id;
    private Integer id;
    private Float value;
    private String name;
    private ClinicalHistory clinicalHistory;

    private Patient patient;


    public Symptom(float value, String name) {
        this.value = value;
        this.name = name;
    }

    public Symptom(float value, String name, ClinicalHistory clinicalHistory) {
        this.value = value;
        this.name = name;
        this.clinicalHistory = clinicalHistory;
    }

    public Symptom(float value, String name, Patient patient) {
        this.value = value;
        this.name = name;
        this.patient = patient;
    }

    public Symptom(Integer id, float value, String name) {
        this.id = id;
        this.value = value;
        this.name = name;
    }


    public Symptom(Integer id, float value, String name, ClinicalHistory clinicalHistory) {
        this.id = id;
        this.value = value;
        this.name = name;
        this.clinicalHistory = clinicalHistory;
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

    public ClinicalHistory getClinicalHistory() {
        return clinicalHistory;
    }

    public void setClinicalHistory(ClinicalHistory clinicalHistory) {
        this.clinicalHistory = clinicalHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Symptom)) return false;
        Symptom symptom = (Symptom) o;
        return Objects.equals(getId(), symptom.getId()) && Objects.equals(getValue(), symptom.getValue()) && Objects.equals(getName(), symptom.getName()) && Objects.equals(getClinicalHistory(), symptom.getClinicalHistory());
    }

    @Override
    public int hashCode() {
        return hash(getId(), getValue(), getName(), getClinicalHistory());
    }

    @Override
    public String toString() {
        return "Symptom{" +
                "id=" + id +
                ", value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}
