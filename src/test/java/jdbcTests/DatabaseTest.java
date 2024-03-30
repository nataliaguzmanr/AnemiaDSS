package jdbcTests;

import diagnosis.*;
import jdbc.*;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class DatabaseTest {

    @Test
    public  void testConnection(){
        JDBCManager jdbcManager = new JDBCManager();
        jdbcManager.disconnect();
        System.exit(0);
    }

    @Test
    public void addPatientTest(){

        JDBCManager jdbcManager = new JDBCManager();
        JDBCPatientManager jdbcPatientManager = new JDBCPatientManager(jdbcManager);

        //Patient p1 = new Patient("Rafael Gomez",55, Gender.MALE);
        Patient p1 = new Patient("Juana Lopez",35, Gender.FEMALE, 67.45F);
        jdbcPatientManager.addPatient(p1);

    }

    @Test
    public void getPatientTest(){

        JDBCManager jdbcManager = new JDBCManager();
        JDBCPatientManager jdbcPatientManager = new JDBCPatientManager(jdbcManager);

        int id1 = 1;

        Patient p = jdbcPatientManager.getPatient(id1);
        System.out.println(p);

    }

    @Test
    public void getAllPatientsTest(){

        JDBCManager jdbcManager = new JDBCManager();
        JDBCPatientManager jdbcPatientManager = new JDBCPatientManager(jdbcManager);

        List<Patient> p_list = jdbcPatientManager.getAllPatients();
        System.out.println(p_list);

    }

/*    @Test
    public void addMedicalStaffTest(){

        JDBCManager jdbcManager = new JDBCManager();
        JDBCMedicalStaffManager jdbcMedicalStaffManager = new JDBCMedicalStaffManager(jdbcManager);

        MedicalStaff ms = new MedicalStaff("Patrick Serrano");
        try {
            jdbcMedicalStaffManager.addMedicalStaff(ms);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    @Test
    public void getMedicalStaffTest(){

        JDBCManager jdbcManager = new JDBCManager();
        JDBCMedicalStaffManager jdbcMeDicalStaffManager = new JDBCMedicalStaffManager(jdbcManager);

        int id1 = 1;
        try {
            MedicalStaff ms = jdbcMeDicalStaffManager.getMedicalStaff(id1);
            System.out.println(ms);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addReportTest(){

        JDBCManager jdbcManager = new JDBCManager();
        JDBCReportManager jdbcReportManager = new JDBCReportManager(jdbcManager);

        Report r = new Report(1, "testReport2.txt", 1);
        jdbcReportManager.addReport(r);

        //patient null
        try{
            jdbcReportManager.addReport(r);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void getReportTest(){

        JDBCManager jdbcManager = new JDBCManager();
        JDBCReportManager jdbcReportManager = new JDBCReportManager(jdbcManager);
        JDBCPatientManager jdbcPatientManager = new JDBCPatientManager(jdbcManager);

        try{

            int id1 =1;
            Patient p = jdbcPatientManager.getPatient(id1);
            System.out.println("ID "+p.getId());

            Report r = jdbcReportManager.getReport(p);
            System.out.println(r);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getReportsListTest(){

        JDBCManager jdbcManager = new JDBCManager();
        JDBCReportManager jdbcReportManager = new JDBCReportManager(jdbcManager);
        JDBCPatientManager jdbcPatientManager = new JDBCPatientManager(jdbcManager);


        try{
            int id1 =1;
            Patient p = jdbcPatientManager.getPatient(id1);
            System.out.println("ID "+p.getId());

            List<Report> r_list = jdbcReportManager.getReportsList(p);
            System.out.println(r_list);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addClinicalHistoryTest(){

        JDBCManager jdbcManager = new JDBCManager();
        JDBCClinicalHistoryManager jdbcClinicalHistoryManager = new JDBCClinicalHistoryManager(jdbcManager);

        try{
            ClinicalHistory ch = new ClinicalHistory(LocalDate.now());
            int patientID =1;
            jdbcClinicalHistoryManager.addClinicalHistory(ch, patientID);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getClinicalHistoryTest(){

        JDBCManager jdbcManager = new JDBCManager();
        JDBCClinicalHistoryManager jdbcClinicalHistoryManager = new JDBCClinicalHistoryManager(jdbcManager);


        try{
            int id1patient =1;
            ClinicalHistory ch = jdbcClinicalHistoryManager.getClinicalHistory(id1patient);
            System.out.println(ch);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void addSymptomTest(){

        JDBCManager jdbcManager = new JDBCManager();
        JDBCSymptomManager jdbcSymptomManager = new JDBCSymptomManager(jdbcManager);
        JDBCPatientManager jdbcPatientManager = new JDBCPatientManager(jdbcManager);
        JDBCClinicalHistoryManager jdbcClinicalHistoryManager = new JDBCClinicalHistoryManager(jdbcManager);


        try{
            int id=1;
            //Patient p = jdbcPatientManager.getPatient(id);
            ClinicalHistory ch = jdbcClinicalHistoryManager.getClinicalHistory(id);
            Symptom s = new Symptom(1.0F, "Vomit", ch);
            jdbcSymptomManager.addSymptom(s,id);
            System.out.println("\nSymptom: " +s);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getSymptomTest(){

        JDBCManager jdbcManager = new JDBCManager();
        JDBCSymptomManager jdbcSymptomManager = new JDBCSymptomManager(jdbcManager);

        try{

            int id1 =1;
            Symptom s = jdbcSymptomManager.getSymptom(id1);
            System.out.println(s);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
