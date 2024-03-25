package jpa;

import POJOS.User;
import ifaces.UserManager;

import javax.management.Query;
import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class JPAUserManager implements UserManager {

    private EntityManager em;
    /**
     * Constructs a new JPAUserManager and initializes the database connection.
     */
    public JPAUserManager() {
        this.connect();
    }
    /**
     * Establishes a connection to the JPA entity manager and initializes the database with default roles if necessary.
     */
    @Override
    public void connect() {
        em = Persistence.createEntityManagerFactory("MedicalStaff_user").createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
        em.getTransaction().commit();

    }
    /**
     * Closes the connection to the JPA entity manager.
     */
    @Override
    public void disconnect() {
        em.close();
    }
    /**
     * Adds a new user to the database.
     *
     * @param u the User object representing the user to be added
     */
    @Override
    public void newUser(User u) {
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
    }
    /**
     * Deletes a user from the database based on the email and password.
     *
     * @param user the email of the user to be deleted
     * @param password the password of the user to be deleted
     */
    @Override
    public void deleteUser(String user, String password) {
        try {
            System.out.println("The user");
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] hash = md.digest();
            Query q = em.createNativeQuery("SELECT * FROM User WHERE user = ? AND password = ?", User.class);
            q.setParameter(1, user);
            q.setParameter(2, hash);
            if (!q.getResultList().isEmpty()) {
                User u = (User) q.getSingleResult();
                em.getTransaction().begin();
                em.remove(u);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean userNameTaken(String user) {
        Query q = em.createNativeQuery("SELECT * FROM User WHERE user = ?", User.class);
        q.setParameter(1, username);
        List<User> userList = (List) q.getResultList();
        if(userList.isEmpty()) {
            return false;
        }
        else {
            return true;
        }

    }

    @Override
    public User checkPassword(String user, String password) {
        Query q=null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] hash = md.digest();
            q = em.createNativeQuery("SELECT * FROM User WHERE id = ? AND password = ?", User.class);
            q.setParameter(1, user);
            q.setParameter(2, hash);

            return (User) q.getSingleResult();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoResultException nre) {
            return null;
        }

        return (User) q.getSingleResult();
    }

}
