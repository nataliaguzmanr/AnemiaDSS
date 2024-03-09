package diagnosis;

import java.util.Objects;

public class Anemia {
    private final Integer Id;
    private AnemiaType anemia;

    //-----------------------------
    private Patient patient;
    //-----------------------------


    public Anemia(Integer id, AnemiaType anemia, Patient patient) {
        Id = id;
        this.anemia = anemia;
        this.patient = patient;
    }

    public Integer getId() {
        return Id;
    }

    public AnemiaType getAnemia() {
        return anemia;
    }

    public void setAnemia(AnemiaType anemia) {
        this.anemia = anemia;
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
        Anemia anemia1 = (Anemia) o;
        return Objects.equals(Id, anemia1.Id) && anemia == anemia1.anemia;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, anemia);
    }

    @Override
    public String toString() {
        return "Anemia{" +
                "Id=" + Id +
                ", anemia=" + anemia +
                '}';
    }
}
