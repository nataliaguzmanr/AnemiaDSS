package ifaces;

import diagnosis.MedicalStaff;

import java.sql.SQLException;

public interface MedicalStaffManager {
    /**
     * Adds a new medical staff member to the database.
     *
     * @param medicalStaff the MedicalStaff object representing the heath proffesional to be added
     * @param user_id id of the user which corresponds to the MedicalStaff
     * @throws SQLException if a database access error occurs
     */
    public void addMedicalStaff(MedicalStaff medicalStaff, int user_id) throws SQLException;


    /**
     * Retrieves a medical staff by its ID.
     *
     * @param medicalStaff_id the medical staff ID
     * @return the MedicalStaff object
     */
    public MedicalStaff getMedicalStaff(int medicalStaff_id) throws SQLException;







    }