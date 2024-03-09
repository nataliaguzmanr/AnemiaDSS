package diagnosis;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Sign {

    private final Integer id;
    private Date date;

    //-----------------------------
    private Patient patient;
    private List<Symptom> symptoms;
    //-----------------------------


    public Sign(Integer id, Date date, Patient patient) {
        this.id = id;
        this.date = date;
        this.patient = patient;
        this.symptoms = new LinkedList<Symptom>();
    }

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sign sign = (Sign) o;
        return Objects.equals(id, sign.id) && Objects.equals(date, sign.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date);
    }

    @Override
    public String toString() {
        return "Sign{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
