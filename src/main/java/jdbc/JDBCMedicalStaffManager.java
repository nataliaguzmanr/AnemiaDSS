package jdbc;

import diagnosis.MedicalStaff;
import ifaces.MedicalStaffManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCMedicalStaffManager implements MedicalStaffManager {


    private JDBCManager medicalStaffManager;
    /**
     * Constructs a new JDBCDoctorManager with the specified JDBCManager.
     *
     * @param jdbcManager the JDBCManager to be used
     */
    public JDBCMedicalStaffManager(JDBCManager jdbcManager) {
        this.medicalStaffManager = jdbcManager;
    }
    /**
     *
     * Adds a new doctor member to the database.
     *
     * @param medicalStaff the Doctor object representing the doctor to be added
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void addMedicalStaff(MedicalStaff medicalStaff, int user_id) throws SQLException {
        try {
            String sql = "INSERT INTO MedicalStaff(medicalStaff_id, name) VALUES (?,?)";
            PreparedStatement prep = medicalStaffManager.getConnection().prepareStatement(sql);
            prep.setInt(1, user_id);
            prep.setString(2, medicalStaff.getName());

            prep.executeUpdate();
            prep.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * Retrieves a patient by its ID.
     *
     * @param medicalStaff_id the patient ID
     * @return the Patient object
     */
    @Override
    public MedicalStaff getMedicalStaff(int medicalStaff_id) throws SQLException {
        MedicalStaff medicalStaff = null;

        try {
            String sql = "SELECT * FROM MedicalStaff WHERE medicalStaff_id = " + medicalStaff_id;
            PreparedStatement pr = medicalStaffManager.getConnection().prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");

                medicalStaff = new MedicalStaff(medicalStaff_id,name);
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicalStaff;
    }
}
