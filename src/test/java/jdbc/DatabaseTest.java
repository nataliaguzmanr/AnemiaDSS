package jdbc;

import diagnosis.*;
import jdbc.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

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

        Patient p1 = new Patient("Rafael Gomez",55, Gender.MALE);
        try {
            jdbcPatientManager.addPatient(p1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getPatientTest(){

        JDBCManager jdbcManager = new JDBCManager();
        JDBCPatientManager jdbcPatientManager = new JDBCPatientManager(jdbcManager);

        int id1 = 1;
        try {
           Patient p = jdbcPatientManager.getPatient(id1);
            System.out.println(p);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addMedicalStaffTest(){

        JDBCManager jdbcManager = new JDBCManager();
        JDBCMedicalStaffManager jdbcMedicalStaffManager = new JDBCMedicalStaffManager(jdbcManager);

        MedicalStaff ms = new MedicalStaff("Patrick Serrano");
        try {
            jdbcMedicalStaffManager.addMedicalStaff(ms);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
