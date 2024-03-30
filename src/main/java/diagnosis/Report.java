package diagnosis;

import java.io.Serializable;
import java.util.Objects;

public class Report implements Serializable {


    private static final long serialVersionUID = -7935685254551156926L;
    private Integer id;
    private String file_name;
    private Patient patient;

    public Report(Integer id, String file_name, Patient patient) {
        this.id = id;
        this.file_name = file_name;
        this.patient = patient;
    }

    //REVISAR
    public Report(Integer id, String file_name, Integer patient_id) {
        this.id = id;
        this.file_name = file_name;
        this.patient = new Patient(patient_id);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
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
        Report report = (Report) o;
        return Objects.equals(id, report.id) && Objects.equals(file_name, report.file_name) && Objects.equals(patient, report.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, file_name, patient);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", file_name='" + file_name + '\'' +
                '}';
    }
}
