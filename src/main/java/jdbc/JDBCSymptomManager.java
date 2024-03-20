package jdbc;

import diagnosis.MedicalStaff;
import diagnosis.Patient;
import diagnosis.Symptom;
import ifaces.SymptomManager;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class JDBCSymptomManager implements SymptomManager {

    private JDBCManager symptomManager;

    public JDBCSymptomManager(JDBCManager m) {
        this.symptomManager = m;
    }

    //this method works
    @Override
    public void addSymptom(Symptom s, int patient_id) throws SQLException {
        try {
            String sql = "INSERT INTO Symptom (value, name, symptom_date, patient_id) VALUES (?, ?, ?, ?)";
            PreparedStatement prep = symptomManager.getConnection().prepareStatement(sql);
            prep.setFloat(1, s.getValue());
            prep.setString(2, s.getName());
            Date date = Date.valueOf(s.getDate());
            prep.setDate(3, date);
            prep.setObject(4, patient_id);


            prep.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Symptom getSymptom(int symptom_id) throws SQLException {
        Symptom symptom = null;

        try {
            String sql = " SELECT * FROM Symptom WHERE symptom_id = " + symptom_id;
            PreparedStatement pr = symptomManager.getConnection().prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {

                Float value = rs.getFloat("value");
                String name = rs.getString("name");
                java.sql.Date sqlDate = rs.getDate("symptom_date");
                LocalDate symp_localDate = sqlDate.toLocalDate();
                //LocalDate symp_localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                //int patient_id = rs.getInt("patient_id"); SOS

                symptom = new Symptom(symptom_id, value, name, symp_localDate);
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return symptom;
    }



}
