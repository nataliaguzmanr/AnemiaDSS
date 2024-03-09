package diagnosis;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MedicalStaff {

    private final Integer id;
    private  String user;
    private  String password;

    //-----------------------------
    private List<Patient> patients;
    //-----------------------------


    public MedicalStaff(Integer id, String user, String password) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.patients = new LinkedList<Patient>();
    }

    public Integer getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalStaff that = (MedicalStaff) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, password);
    }

    @Override
    public String toString() {
        return "MedicalStaff{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", patients=" + patients +
                '}';
    }
}
