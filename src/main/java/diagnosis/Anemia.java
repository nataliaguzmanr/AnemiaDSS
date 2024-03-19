package diagnosis;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Anemia implements Serializable {


    @Serial
    private static final long serialVersionUID = -9096161678878767301L;

    private final Integer Id;
    private AnemiaType anemia;
    private List<Patient> patients;


    public Anemia(Integer id, AnemiaType anemia) {
        Id = id;
        this.anemia = anemia;
        this.patients = new LinkedList<Patient>();
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

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
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
