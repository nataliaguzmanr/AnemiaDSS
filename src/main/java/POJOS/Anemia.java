package POJOS;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Anemia implements Serializable {

    private static final long serialVersionUID = -9096161678878767301L;

    private final Integer Id;
    private AnemiaType anemiaType;
    //private List<Patient> patients;
    private final float[] weights;

    public Anemia(Integer id, AnemiaType anemia, float[] weights) {
        Id = id;
        this.anemiaType = anemia;
        this.weights = weights;
        //this.patients = new LinkedList<Patient>();
    }

    //getScore()


    public Integer getId() {
        return Id;
    }

    public AnemiaType getAnemiaType() {
        return anemiaType;
    }

    public void setAnemiaType(AnemiaType anemiaType) {
        this.anemiaType = anemiaType;
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
        return Objects.equals(Id, anemia1.Id) && anemiaType == anemia1.anemiaType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, anemiaType);
    }

    @Override
    public String toString() {
        return "Anemia{" +
                "Id=" + Id +
                ", anemia=" + anemiaType +
                '}';
    }
}
