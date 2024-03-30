package diagnosis;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ClinicalHistory {

    private Integer id;
    private LocalDate symptomsDate;
    private List<Patient> patientsHistory;


    public ClinicalHistory(Integer id, LocalDate symptomsDate, List<Patient> symtompsHistory) {
        this.id = id;
        this.symptomsDate = symptomsDate;
        this.patientsHistory = symtompsHistory;
    }

    public ClinicalHistory(Integer id, LocalDate symptomsDate) {
        this.id = id;
        this.symptomsDate = symptomsDate;
    }

    public ClinicalHistory(LocalDate symptomsDate) {
        this.symptomsDate = symptomsDate;

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

    public List<Patient> getPatientsHistory() {
        return patientsHistory;
    }

    public void setPatientsHistory(List<Patient> patientsHistory) {
        this.patientsHistory = patientsHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClinicalHistory)) return false;
        ClinicalHistory that = (ClinicalHistory) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getSymptomsDate(), that.getSymptomsDate()) && Objects.equals(getPatientsHistory(), that.getPatientsHistory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSymptomsDate(), getPatientsHistory());
    }

    @Override
    public String toString() {
        return "ClinicalHistory{" +
                "id=" + id +
                ", symptomsDate=" + symptomsDate +
                '}';
    }
}
