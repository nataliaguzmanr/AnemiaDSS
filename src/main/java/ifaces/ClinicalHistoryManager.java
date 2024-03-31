package ifaces;

import diagnosis.ClinicalHistory;
import diagnosis.Patient;

import java.sql.SQLException;
import java.util.List;

public interface ClinicalHistoryManager {

    /**
     * Adds a new patient to the database.
     *
     * @param clinicalHistory the Doctor object representing the doctor to be added
     * @param patient_id the id of the patient which belongs the clinical history
     * @throws SQLException if a database access error occurs
     */
    public void addClinicalHistory(ClinicalHistory clinicalHistory, int patient_id) throws SQLException;



    /**
     * Retrieves a Clinical history by its ID.
     *
     * @param patient_id the symptom ID
     * @return the ClinicalHistory object
     */
    public ClinicalHistory getClinicalHistory(int patient_id) throws SQLException;

    public List<ClinicalHistory> getHistoriesList(Patient p);
}
