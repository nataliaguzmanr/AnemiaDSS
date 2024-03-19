package ifaces;

import java.sql.SQLException;

public interface MedicalStaffManager {
    /**
     * Adds a new doctor member to the database.
     *
     * @param medicalStaffmember the Doctor object representing the doctor to be added
     * @throws SQLException if a database access error occurs
     */
    //public void addMedicalStaffMember(MedicalStaff medicalStaffmember) throws SQLException;


    /**
     * Searches for a doctor ID based on the user ID.
     *
     * @param id the user ID
     * @return the doctor ID
     */
    public int searchMedicalStaffIdfromUId(int id);


    /**
     * Searches for a doctor by their ID.
     *
     * @param id the doctor ID
     * @return the Doctor object
     */
    //public MedicalStaff searchMedicalSatffbyId(int id);


    /**
     * Updates the information of a doctor member in the database.
     *
     * @param medicalStaffmember the Doctor object representing the updated doctor information
     */
    //public void updateMedicalStaffMemberInfo(MedicalStaff medicalStaffmember);


    /**
     * Retrieves a list of all doctors from the database.
     *
     * @return an ArrayList of Doctor objects representing all the doctors
     */
    //public ArrayList<MedicalStaff> searchAllMedicalStaff();

}