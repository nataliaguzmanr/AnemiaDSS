package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCManager {

    private Connection c = null;


    /**
     * Constructs a new JDBCManager and initializes the database connection.
     */
    public JDBCManager() {
        try {
            // Open the DB connection
            Class.forName("org.sqlite.JDBC");
            String dbPath = "/db/AnemiaDSSdb.db";

            c = DriverManager.getConnection("jdbc:sqlite:." + dbPath);
            c.createStatement().execute("PRAGMA foreign_keys=ON");

            //System.out.println("Database connection opened.");
            // create tables
            this.createTables();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.print("Libraries not loaded");
        }
    }



    /**
     * Closes the database connection.
     */
    public void disconnect() {
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Retrieves the database connection.
     *
     * @return the database connection
     */
    public Connection getConnection() {
        return c;
    }


    /**
     * Creates the necessary tables in the database if they do not already exist.
     */
    private void createTables() {
        // Create Tables
        try {
            Statement stmt = c.createStatement();

            // TABLE PATIENT
            String sql = "CREATE TABLE Patient (" + "	patient_id	    INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "	name TEXT NOT NULL,"
                    + "	gender TEXT NOT NULL,"
                    + "	age	INTEGER,"
                    + "	appointment_date DATE,"
                    + " medicalStaff_id	INTEGER REFERENCES MedicalStaff(medicalStaff_id),"
                    + "symptoms_id INTEGER REFERENCES Symptom(symptom_id)"
                    + "anemia_id INTEGER REFERENCES Anemia(anemia_id)"
                    +");";
            stmt.executeUpdate(sql);

/*            // TABLE DOCTOR (OK)
            sql = "CREATE TABLE MedicalStaff (" + " medicalStaff_id	INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " name		TEXT NOT NULL," + " dob 		DATE," + " address 	TEXT NOT NULL,"
                    + " phone 		INTEGER NOT NULL," + " email 		TEXT" + ");";
            stmt.executeUpdate(sql);
            // TABLE TASK AÑADIR ELDERLY ID, AÑADIR DE DECORACION UN COLUMNA DE HORARIO SI
            // QUEREIS
            sql = "CREATE TABLE Task (" + "	task_id			INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "	description		TEXT NOT NULL," + " doctor_id		INTEGER REFERENCES Doctor(doctor_id),"
                    + " duration		INTEGER NOT NULL," + " elderly_id 		INTEGER REFERENCES Elderly(elderly_id)"
                    + ");";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE Report (" + " report_id	    INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " file_name		TEXT NOT NULL,"
                    + " patient_id 		INTEGER REFERENCES Patient(patient_id)" + ");";
            stmt.executeUpdate(sql);

*/
        } catch (SQLException e) {
            // Do not compile if tables already exist
            if (!e.getMessage().contains("already exists")) {
                e.printStackTrace();
            }
        }
    }

}
