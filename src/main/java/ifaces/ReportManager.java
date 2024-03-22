package ifaces;

import diagnosisTests.Patient;
import diagnosisTests.Report;

import java.util.List;

public interface ReportManager {

    public void addReport(Report r);
    public Report getReport(Patient p);
    public List<Report> getReportsList(Patient p);
}
