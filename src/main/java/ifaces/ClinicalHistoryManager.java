package ifaces;

import POJOS.ClinicalHistory;

import java.sql.SQLException;

public interface ClinicalHistoryManager {

    /**
     * Adds a new patient to the database.
     *
     * @param clinicalHistory the Doctor object representing the doctor to be added
     * @throws SQLException if a database access error occurs
     */
    public void addClinicalHistory(ClinicalHistory clinicalHistory) throws SQLException;



    /**
     * Retrieves a Clinical history by its ID.
     *
     * @param patient_id the symptom ID
     * @return the ClinicalHistory object
     */
    public ClinicalHistory getClinicalHistory(int patient_id) throws SQLException;
}
