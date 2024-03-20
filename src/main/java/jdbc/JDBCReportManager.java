package jdbc;

import diagnosis.Patient;
import diagnosis.Report;
import ifaces.ReportManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class JDBCReportManager implements ReportManager {
    private JDBCManager manager;

    public JDBCReportManager(JDBCManager m) {
        this.manager = m;
    }

    //this method works
    @Override
    public void addReport(Report r) {
        try {
            String sql = "INSERT INTO Report (file_name, patient_id) VALUES (?, ?)";
            PreparedStatement prep = manager.getConnection().prepareStatement(sql);
            prep.setString(1, r.getFile_name());
            prep.setInt(2, r.getPatient().getId());
            prep.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get the last report that has been inserted into the database (the most recent one)
    @Override
    public Report getReport(Integer patient_id) {
        Report r = null;
        try {
            String sql = "SELECT * FROM Report WHERE patient_id=? ORDER BY report_id DESC LIMIT 1";
            PreparedStatement prep = manager.getConnection().prepareStatement(sql);
            prep.setInt(1, patient_id);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("report_id");
                String file_name = rs.getString("file_name");

                Patient p = new Patient(patient_id);
                r = new Report(id, file_name, p);
            }
            rs.close();
            prep.close();

        }catch(Exception e) {
            e.printStackTrace();
        }

        return r;
    }

    @Override
    public List<Report> getReports(Integer patient_id) {
        List<Report> reports = new LinkedList<>();
        Report r = null;
        try {
            String sql = "SELECT * FROM Report WHERE patient_id=?";
            PreparedStatement prep = manager.getConnection().prepareStatement(sql);
            prep.setInt(1, patient_id);
            ResultSet rs = prep.executeQuery();

            while(rs.next()) {
                Integer report_id = rs.getInt(1);
                String file_name = rs.getString(2);
                Patient p = new Patient(patient_id);
                r = new Report(report_id, file_name, p);
                reports.add(r);
            }
            rs.close();
            prep.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return reports;
    }





}
