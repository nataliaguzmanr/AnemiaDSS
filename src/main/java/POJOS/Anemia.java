package POJOS;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Anemia implements Serializable {

    private static final long serialVersionUID = -9096161678878767301L;

    private final Integer id;
    private AnemiaType anemiaType;
    private List<Float> weights;
    private List<Condition> conditions;

    public Anemia(Integer id, AnemiaType anemia, List<Float> weights, List<Condition> conditions) {
        this.id = id;
        this.anemiaType = anemia;
        this.weights = weights;
        this.conditions = conditions;
    }

    public Anemia(Integer id, AnemiaType anemiaType) {
        this.id = id;
        this.anemiaType = anemiaType;
    }

    //getScore()


    public Integer getId() {
        return id;
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
        return Objects.equals(id, anemia1.id) && anemiaType == anemia1.anemiaType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, anemiaType);
    }

    @Override
    public String toString() {
        return "Anemia{" +
                "Id=" + id +
                ", anemia=" + anemiaType +
                '}';
    }
}
