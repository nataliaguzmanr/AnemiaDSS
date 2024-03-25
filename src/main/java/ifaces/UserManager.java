package ifaces;

import POJOS.User;

public interface UserManager {

    /**
     * Establishes a connection to the database.
     */
    public void connect();


    /**
     * Closes the connection to the database.
     */
    public void disconnect();


    /**
     * Adds a new user to the database.
     *
     * @param u the User object representing the user to be added
     */
    public void newUser(User u);


    /**
     * Deletes a user from the database based on the email and password.
     *
     * @param email the email of the user to be deleted
     * @param password the password of the user to be deleted
     */
    public void deleteUser(String email, String password);


    /**
     * Checks if the user is already being used
     *
     * @param user the id of the user
     * @returns true or false statement
     */
    public boolean userNameTaken(String user);


    /**
     * Checks the password of a user.
     *
     * @param user the username of the user
     * @param password the password digest of the user
     * @return the User object if the password is correct, otherwise null
     */
    public User checkPassword(String user, String password);
}
