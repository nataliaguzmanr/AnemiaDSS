package jdbc;

import diagnosis.Report;
import diagnosis.User;
import ifaces.UserManager;
import utilities.*;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class JDBCUserManager implements UserManager {

    private JDBCManager userManager;
    private final static char XOR_KEY = 'k';

    public JDBCUserManager(JDBCManager m) {
        this.userManager = m;
    }

    /**
     * Adds a new user to the database.
     * @param u the User object representing the user to be added
     */
    @Override

    public void newUser(User u) {
        try {
            String sql = "INSERT INTO User (user_name, encr_password) VALUES (?, ?)";
            PreparedStatement prep = userManager.getConnection().prepareStatement(sql);
            prep.setString(1, u.getUser());
            String encrypted_password = Utilities.xor_encrypt_decrypt(u.getPassword(), XOR_KEY);
            prep.setString(2, encrypted_password);
            prep.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public boolean userNameTaken(String user_name) {
        Boolean present = false;
        try {
            String sql =  "SELECT * FROM User WHERE user_name = ?";
            PreparedStatement prep = userManager.getConnection().prepareStatement(sql);
            prep.setString(1, user_name);
            ResultSet rs = prep.executeQuery();

            while(rs.next()) {
                present = true;
            }
            rs.close();
            prep.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return present;
    }

    @Override
    public User checkPassword(String user_name, String password) {

        User u = null;

        try {

            String sql = "SELECT * FROM User WHERE user_name = ? AND encr_password = ?";
            PreparedStatement prep = userManager.getConnection().prepareStatement(sql);
            prep.setString(1, user_name);
            prep.setString(2, Utilities.xor_encrypt_decrypt(password, XOR_KEY));


            ResultSet rs = prep.executeQuery();

            while(rs.next()) {
                Integer user_id = rs.getInt(1);
                String userName = rs.getString(2);
                String encr_password = rs.getString(3);

                if (encr_password != null &&
                        Utilities.xor_encrypt_decrypt(encr_password, XOR_KEY).equals(password)) {
                    u = new User(user_id, userName, password);
                    System.out.println("Login successful!");
                }
            }
            rs.close();
            prep.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

}
