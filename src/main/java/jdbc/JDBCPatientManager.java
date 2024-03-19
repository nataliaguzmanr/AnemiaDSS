package jdbc;

import diagnosis.*;
public class JDBCPatientManager {

    private JDBCManager patientManager;
    /**
     * Constructs a new JDBCDoctorManager with the specified JDBCManager.
     *
     * @param jdbcManager the JDBCManager to be used
     */
    public JDBCPatientManager(JDBCManager jdbcManager) {
        this.patientManager = jdbcManager;
    }
    /**
     *
     * Adds a new doctor member to the database.
     *
     * @param patient the Doctor object representing the doctor to be added
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void addPatient(Patient patient) throws SQLException {
        try {
            String sql = "INSERT INTO Doctor (name, gender, age) VALUES (?,?,?,?,?)";
            PreparedStatement prep = patientManager.getConnection().prepareStatement(sql);
            prep.setString(1, patient.getName());

            java.sql.Date sqlDate = new java.sql.Date(Doctormember.getDob().getTime());
            prep.setDate(2, sqlDate);
            prep.setString(3, Doctormember.getAddress());
            prep.setInt(4, Doctormember.getPhone());
            prep.setString(5, Doctormember.getEmail());
            prep.executeUpdate();
            prep.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
