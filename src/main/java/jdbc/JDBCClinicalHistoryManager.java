package jdbc;

import diagnosis.ClinicalHistory;
import ifaces.ClinicalHistoryManager;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class JDBCClinicalHistoryManager implements ClinicalHistoryManager {

    private JDBCManager clinicalHistoryManager;

    /**
     * Constructs a new JDBCMedicalHistoryManager with the specified JDBCManager.
     *
     * @param jdbcManager the JDBCManager to be used
     */
    public JDBCClinicalHistoryManager(JDBCManager jdbcManager) {
        this.clinicalHistoryManager = jdbcManager;
    }

    /**
     *
     * Adds a new patient to the database.
     *
     * @param clinicalHistory the Doctor object representing the doctor to be added
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void addClinicalHistory(ClinicalHistory clinicalHistory, int patient_Id) throws SQLException{
        try {
            String sql = "INSERT INTO ClinicalHistory(symptoms_date, patient_id) VALUES (?,?)";
            PreparedStatement prep = clinicalHistoryManager.getConnection().prepareStatement(sql);
            Date date = Date.valueOf(clinicalHistory.getSymptomsDate());
            prep.setDate(1, date);
            prep.setInt(2, patient_Id);

            prep.executeUpdate();
            prep.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    @Override
    public ClinicalHistory getClinicalHistory(int patient_id) throws SQLException {
        ClinicalHistory clinicalHistory = null;

        try {
            //Date sqlDATE = ((Date) date.getClass()));
            String sql = " SELECT * FROM ClinicalHistory WHERE patient_id = " + patient_id;
            PreparedStatement pr = clinicalHistoryManager.getConnection().prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {

                int id = rs.getInt("clinicalHistory_id");
                Date sqlDate = rs.getDate("symptoms_date");
                LocalDate symp_localDate = sqlDate.toLocalDate();

                clinicalHistory = new ClinicalHistory(id,symp_localDate);
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clinicalHistory;
    }

}
