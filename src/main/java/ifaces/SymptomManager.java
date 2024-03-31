package ifaces;

import diagnosis.Symptom;

import java.sql.SQLException;
import java.util.List;

public interface SymptomManager {

    /**
     * Adds a new symptom to the database.
     *
     * @param s the Symptom object r to be added
     * @param patient_id id of the Patient which suffers the symptom added
     * @throws SQLException if a database access error occurs
     */
    public void addSymptom(Symptom s, int patient_id) throws SQLException;

    /**
     * Retrieves a symptom by its ID.
     *
     * @param symptom_id the symptom ID
     * @return the Symptom object
     */
    public Symptom getSymptom(int symptom_id) throws SQLException;
    public List<Symptom> getSymptomsList(Integer clinicalHistoryId);
}
