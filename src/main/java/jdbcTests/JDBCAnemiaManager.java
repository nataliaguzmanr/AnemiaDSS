package jdbcTests;

import diagnosisTests.Anemia;
import diagnosisTests.AnemiaType;
import ifaces.AnemiaManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCAnemiaManager implements AnemiaManager {

    private JDBCManager anemiaManager;

    public JDBCAnemiaManager(JDBCManager m) {
        this.anemiaManager = m;
    }

    public Anemia getAnemia(Integer anemia_id){
        Anemia anemia = null;

        try {
            String sql = " SELECT * FROM Anemia WHERE anemiaTable_id = ?";
            PreparedStatement prep = anemiaManager.getConnection().prepareStatement(sql);
            prep.setInt(1, anemia_id);
            ResultSet rs = prep.executeQuery();
            if (rs.next()) {

                String string_type = rs.getString("anemia_Type");
                AnemiaType type = AnemiaType.valueOf(string_type);
                Integer patient_id = rs.getInt("patient_id");

                anemia = new Anemia(anemia_id,type);
            }
            rs.close();
            prep.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return anemia;
    }

}
