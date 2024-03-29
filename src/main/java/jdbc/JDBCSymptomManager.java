package jdbc;

import diagnosis.Symptom;
import ifaces.SymptomManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCSymptomManager implements SymptomManager {

    private JDBCManager symptomManager;

    public JDBCSymptomManager(JDBCManager m) {
        this.symptomManager = m;
    }

    //this method works
    @Override
    public void addSymptom(Symptom symp, int clinicalHistory_id) throws SQLException {
        try {
            String sql = "INSERT INTO Symptom (value, name, clinicalHistory_id) VALUES (?, ?, ?)";
            PreparedStatement prep = symptomManager.getConnection().prepareStatement(sql);
            prep.setFloat(1, symp.getValue());
            prep.setString(2, symp.getName());
            //Date date = Date.valueOf(s.getDate());
            //prep.setDate(3, date);
            prep.setObject(3, clinicalHistory_id);


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
                //Date sqlDate = rs.getDate("symptom_date");
                //LocalDate symp_localDate = sqlDate.toLocalDate();

                symptom = new Symptom(symptom_id, value, name);
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return symptom;
    }



}
