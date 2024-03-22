package diagnosis;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ClinicalHistory {

    private Integer id;
    private LocalDate symptomsDate;
    private List<Symptom> symtompsHistory;


    public ClinicalHistory(Integer id, LocalDate symptomsDate, List<Symptom> symtompsHistory) {
        this.id = id;
        this.symptomsDate = symptomsDate;
        this.symtompsHistory = symtompsHistory;
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

    public List<Symptom> getSymtompsHistory() {
        return symtompsHistory;
    }

    public void setSymtompsHistory(List<Symptom> symtompsHistory) {
        this.symtompsHistory = symtompsHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClinicalHistory)) return false;
        ClinicalHistory that = (ClinicalHistory) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getSymptomsDate(), that.getSymptomsDate()) && Objects.equals(getSymtompsHistory(), that.getSymtompsHistory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSymptomsDate(), getSymtompsHistory());
    }

    @Override
    public String toString() {
        return "\nClinicalHistory{" +
                "id=" + id +
                ", symptomsDate=" + symptomsDate +
                ", symtompsHistory=" + symtompsHistory +
                '}';
    }
}
