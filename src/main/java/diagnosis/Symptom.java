package diagnosis;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Symptom {

    private final Integer id;
    private final float value;
    private final String name;
    private Patient patient;


    public Symptom(Integer id, float value, String name, Patient patient) {
        this.id = id;
        this.value = value;
        this.name = name;
        this.patient = patient;
    }

    public Integer getId() {
        return id;
    }

    public float getValue() {
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
        if (!(o instanceof Symptom)) return false;
        Symptom symptom = (Symptom) o;
        return Float.compare(getValue(), symptom.getValue()) == 0 && Objects.equals(getId(), symptom.getId()) && Objects.equals(getName(), symptom.getName()) && Objects.equals(getPatient(), symptom.getPatient());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getValue(), getName(), getPatient());
    }

    @Override
    public String toString() {
        return "Symptom{" +
                "id=" + id +
                ", value=" + value +
                ", name='" + name + '\'' +
                ", patient=" + patient +
                '}';
    }


}
