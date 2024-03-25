package ifaces;

import POJOS.Patient;

import java.sql.SQLException;
import java.util.List;

public interface PatientManager {

    /**
     * Adds a new patient to the database.
     *
     * @param patient the Doctor object representing the doctor to be added
     * @throws SQLException if a database access error occurs
     */
    public void addPatient(Patient patient) throws SQLException;

    /**
     * Retrieves a patient by its ID.
     *
     * @param patient_id the patient ID
     * @return the Patient object
     */
    public Patient getPatient(int patient_id) throws SQLException;

    /**
     * Retrieves all patients in data base.
     *
     * @return a list of Patients object
     */
    public List<Patient> getAllPatients() throws SQLException;
}
