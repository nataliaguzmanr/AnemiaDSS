package ifaces;

import diagnosis.Report;

import java.util.List;

public interface ReportManager {

    public void addReport(Report r);
    public Report getReport(Integer patient_id);
    public List<Report> getReports(Integer patient_id);
}
