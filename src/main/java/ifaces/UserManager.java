package ifaces;

import diagnosis.User;

public interface UserManager {


    /**
     * Adds a new user to the database.
     *
     * @param u the User object representing the user to be added
     */
    public void newUser(User u);



    /**
     * Checks if the user is already being used
     *
     * @param user_name name of the user
     * @returns true or false statement
     */
    public boolean userNameTaken(String user_name);


    /**
     * Checks the password of a user.
     *
     * @param user the username of the user
     * @param password the password digest of the user
     * @return the User object if the password is correct, otherwise null
     */
    public User checkPassword(String user, String password);
}
