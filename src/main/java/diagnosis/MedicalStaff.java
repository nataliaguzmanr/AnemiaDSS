package diagnosis;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.io.Serializable;

public class MedicalStaff implements Serializable{


    private static final long serialVersionUID = 881034396276971561L;
    private final Integer id;
    private  String name;
    private List<Patient> patients;


    public MedicalStaff(Integer id, String user)  {
        this.id = id;
        this.name = user;
  //      this.password = password;
        this.patients = new LinkedList<Patient>();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalStaff that = (MedicalStaff) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "MedicalStaff{" +
                "id=" + id +
                ", user='" + name + '\'' +
                ", patients=" + patients +
                '}';
    }
}
