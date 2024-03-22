package diagnosis;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Anemia implements Serializable {

    private static final long serialVersionUID = -9096161678878767301L;

    private final Integer Id;
    private AnemiaType anemiaType;
    private List<Float> weights;
    private List<Condition> conditions;

    public Anemia(Integer id, AnemiaType anemia, List<Float> weights, List<Condition> conditions) {
        Id = id;
        this.anemiaType = anemia;
        this.weights = weights;
        this.conditions = conditions;
    }

    public Anemia(Integer id, AnemiaType anemiaType) {
        Id = id;
        this.anemiaType = anemiaType;
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

    public List<Float> getWeights() {
        return weights;
    }

    public List<Condition> getConditions() {
        return conditions;
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
