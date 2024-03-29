package jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import diagnosis.Gender;
import diagnosis.Patient;
import ifaces.PatientManager;

public class JDBCPatientManager implements PatientManager {


    private JDBCManager patientManager;
    /**
     * Constructs a new JDBCPatientManager with the specified JDBCManager.
     *
     * @param jdbcManager the JDBCManager to be used
     */
    public JDBCPatientManager(JDBCManager jdbcManager) {
        this.patientManager = jdbcManager;
    }
    /**
     *
     * Adds a new patient to the database.
     *
     * @param patient the Doctor object representing the doctor to be added
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void addPatient(Patient patient)  {
        try {
            String sql = "INSERT INTO Patient(name, age, gender, weight) VALUES (?,?,?,?)";
            PreparedStatement prep = patientManager.getConnection().prepareStatement(sql);
            prep.setString(1, patient.getName());
            prep.setInt(2, patient.getAge());
            prep.setString(3, patient.getGender().toString());
            prep.setFloat(4, patient.getWeight());

            prep.executeUpdate();
            prep.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * Retrieves a patient by its ID.
     *
     * @param patient_id the patient ID
     * @return the Patient object
     */
    @Override
    public Patient getPatient(int patient_id){
        Patient patient = null;
        try {
            String sql = "SELECT * FROM Patient WHERE patient_id = " + patient_id;
            PreparedStatement pr = patientManager.getConnection().prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String gender_str = rs.getString("gender");
                Gender gender = Gender.valueOf(gender_str);
                int age = rs.getInt("age");
                float weight = rs.getFloat("weight");

                patient = new Patient(patient_id, name, age, gender, weight);
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patient;
    }

    @Override
    public List<Patient> getAllPatients() {

        List<Patient> patients = new LinkedList<>();
        Patient p = null;
        try {
            String sql = "SELECT * FROM Patient" ;
            PreparedStatement pr = patientManager.getConnection().prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int patient_id = rs.getInt("patient_id");
                String name = rs.getString("name");
                String gender_str = rs.getString("gender");
                Gender gender = Gender.valueOf(gender_str);
                int age = rs.getInt("age");
                Float weight = rs.getFloat("weight");

                p = new Patient(patient_id, name, age, gender, weight);
                patients.add(p);
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

}


