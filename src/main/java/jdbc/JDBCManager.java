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
            //DriverManager.registerDriver(new org.sqlite.JDBC());
            String dbPath = "/db/AnemiaDSSdb.db";

            c = DriverManager.getConnection("jdbc:sqlite:." + dbPath);
            c.createStatement().execute("PRAGMA foreign_keys=ON");

            if (c != null) {
                System.out.print("Conected succesfully\n");
            } else {
                System.out.print("Conection unsuccesful\n");
            }

            System.out.println("Database connection opened.");
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

            //TABLE MEDICAL STAFF - PATIENT
            String sql = "CREATE TABLE MedicalStaff_Patient ("
                    + " patient_id INTEGER REFERENCES Patient(patient_id), "
                    + " medicalStaff_id INTEGER REFERENCES MedicalStaff(medicalStaff_id), "
                    + " PRIMARY KEY (patient_id, medicalStaff_id)"
                    +");";
            stmt.executeUpdate(sql);


            // TABLE SYMPTOMS
            sql = "CREATE TABLE Symptom ("
                    + " symptom_id	INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " value FLOAT NOT NULL,"
                    + " name TEXT NOT NULL,"
                    + " symptom_date DATE NOT NULL,"
                    + " patient_id INTEGER REFERENCES Patient(patient_id) "
                    +");";
            stmt.executeUpdate(sql);

            // TABLE PATIENT
             sql = "CREATE TABLE Patient ("
                    + "	patient_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "	name TEXT NOT NULL,"
                    + "	gender TEXT NOT NULL,"
                    + "	age	INTEGER,"
                    + " medicalStaff_id	INTEGER REFERENCES MedicalStaff(medicalStaff_id),"
                    + "symptoms_id INTEGER REFERENCES Symptom(symptom_id),"
                    + "anemia_id INTEGER REFERENCES Anemia(anemiaTable_id)"
                    +");";
            stmt.executeUpdate(sql);

            // TABLE MEDICAL STAFF
            sql = "CREATE TABLE MedicalStaff ("
                    + " medicalStaff_id	INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " name TEXT NOT NULL,"
                    + " patient_id INTEGER REFERENCES Patient(patient_id) "
                    +");";
            stmt.executeUpdate(sql);



            // TABLE ANEMIA
            sql = "CREATE TABLE Anemia ("
                    + "	anemiaTable_id	INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "	anemia_type	TEXT NOT NULL,"
                    + " patient_id INTEGER REFERENCES Patient(patient_id)"
                    + ");";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE Report ("
                    + " report_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " file_name TEXT NOT NULL,"
                    + " patient_id 	INTEGER REFERENCES Patient(patient_id)"
                    + ");";
            stmt.executeUpdate(sql);


        } catch (SQLException e) {
            // Do not compile if tables already exist
            if (!e.getMessage().contains("already exists")) {
                e.printStackTrace();
            }
        }
    }

}
