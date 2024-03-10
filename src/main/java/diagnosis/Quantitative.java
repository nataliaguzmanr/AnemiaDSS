package diagnosis;

import java.util.Objects;

public class Quantitative extends Symptom {

    private Float numericValue;

    public Quantitative(Integer id, String name, Float numericValue) {
        super(id, name);
        this.numericValue = numericValue;
    }

    public Float getNumericValue() {
        return numericValue;
    }

    public void setNumericValue(Float numericValue) {
        this.numericValue = numericValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Quantitative that = (Quantitative) o;
        return Objects.equals(numericValue, that.numericValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numericValue);
    }

    @Override
    public String toString() {
        return "Quantitative{" +
                "id=" + this.getId() +
                ", name=" + this.getName() +
                ", numericValue=" + numericValue +
                '}';
    }
}
