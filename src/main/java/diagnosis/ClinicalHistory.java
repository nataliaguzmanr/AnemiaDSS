package diagnosis;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ClinicalHistory {

    private Integer id;
    private LocalDate symptomsDate;
    private Patient patient;


    public ClinicalHistory(Integer id, LocalDate symptomsDate, Patient p) {
        this.id = id;
        this.symptomsDate = symptomsDate;
        this.patient = p;
    }

    public ClinicalHistory(Integer id, LocalDate symptomsDate) {
        this.id = id;
        this.symptomsDate = symptomsDate;
    }

    public ClinicalHistory(LocalDate symptomsDate) {
        this.symptomsDate = symptomsDate;
    }
    public ClinicalHistory(LocalDate symptomsDate, Patient p) {
        this.symptomsDate = symptomsDate;
        this.patient = p;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getSymptomsDate() {
        return symptomsDate;
    }

    public void setSymptomsDate(LocalDate symptomsDate) {
        this.symptomsDate = symptomsDate;
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
        ClinicalHistory that = (ClinicalHistory) o;
        return Objects.equals(id, that.id) && Objects.equals(symptomsDate, that.symptomsDate) && Objects.equals(patient, that.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symptomsDate, patient);
    }

    @Override
    public String toString() {
        return "ClinicalHistory{" +
                "id=" + id +
                ", symptomsDate=" + symptomsDate +
                '}';
    }
}
