package jdbc;

import diagnosis.Patient;
import diagnosis.Report;
import diagnosis.Symptom;
import ifaces.SymptomManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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
    @Override
    public List<Symptom> getSymptomsList(Integer clinicalHistoryId) {

        List<Symptom> symptoms = new LinkedList<>();
        Symptom s = null;
        try {
            String sql = "SELECT * FROM Symptom WHERE clinicalHistory_id=?";
            PreparedStatement prep = symptomManager.getConnection().prepareStatement(sql);
            prep.setInt(1, clinicalHistoryId);
            ResultSet rs = prep.executeQuery();

            while(rs.next()) {
                Integer symptom_id = rs.getInt(1);
                Float value = rs.getFloat("value");
                String name = rs.getString("name");
                s = new Symptom (symptom_id, value, name);
                symptoms.add(s);
            }
            rs.close();
            prep.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return symptoms;
    }


}
