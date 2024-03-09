package diagnosis;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Symptom {

    private final Integer id;
    private String name;

    //-----------------------------
    private List<Sign> signs;
    //-----------------------------

    public Symptom(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.signs = new LinkedList<Sign>();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Sign> getSigns() {
        return signs;
    }

    public void setSigns(List<Sign> signs) {
        this.signs = signs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symptom symptom = (Symptom) o;
        return Objects.equals(id, symptom.id) && Objects.equals(name, symptom.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Symptom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
