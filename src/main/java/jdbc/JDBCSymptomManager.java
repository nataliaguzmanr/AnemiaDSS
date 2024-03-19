package jdbc;

import diagnosis.Symptom;
import ifaces.SymptomManager;

public class JDBCSymptomManager implements SymptomManager {

    private JDBCManager manager;

    public JDBCSymptomManager(JDBCManager m) {
        this.manager = m;
    }

    //this method works
    @Override
    public void addSymptom(Symptom s) {
        try {
            String sql = "INSERT INTO Symptom (latitude, longitude) VALUES (?, ?)";
            PreparedStatement prep = manager.getConnection().prepareStatement(sql);
            prep.setFloat(1, l.getLatitude());
            prep.setFloat(2, l.getLongitude());
            prep.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
